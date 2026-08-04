package com.eurobuddha.entropy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private final Handler main = new Handler(Looper.getMainLooper());

    private NodeApi node;
    private RollEngine engine;
    private DiceView diceView;
    private LastWordView lastWordView;
    private String selfTestErr = "not run";
    private volatile boolean engineReady = false;

    public RollEngine engine() { return engine; }
    public String selfTestError() { return selfTestErr; }
    public boolean engineReady() { return engineReady; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Seed phrases on screen: never allow screenshots or task-switcher previews.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);

        // edge-to-edge: keep content clear of the status/navigation bars (casino pattern)
        android.view.View root = findViewById(R.id.root);
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            androidx.core.graphics.Insets bars =
                    insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        Theme.load(this);
        Sfx.init(this);
        TextView btnSound = findViewById(R.id.btnSound);
        btnSound.setText(Theme.sound() ? "🔊" : "🔇");
        btnSound.setOnClickListener(v -> {
            Theme.setSound(this, !Theme.sound());
            btnSound.setText(Theme.sound() ? "🔊" : "🔇");
            if (Theme.sound()) Sfx.click();
        });
        try {
            Bip39.init(this);
            selfTestErr = Bip39.selfTest();
        } catch (Exception e) {
            selfTestErr = "init failed: " + e.getMessage();
        }

        // node IPC to the separately installed Minima Core app
        node = new NodeApi(this, enabled -> {
            // the banner is repurposed for a self-test failure — never let pairing hide that
            if (selfTestErr == null)
                findViewById(R.id.pairingBanner).setVisibility(enabled ? android.view.View.GONE : android.view.View.VISIBLE);
            if (enabled) verifyNode();
        });
        engine = new RollEngine(node);

        diceView = new DiceView(this);
        lastWordView = new LastWordView(this);

        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager pager = findViewById(R.id.pager);
        MainPager adapter = new MainPager(
                new BaseView[]{diceView, lastWordView},
                new String[]{"🎲 DICE ROLL", "✓ LAST WORD"});
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(2);
        tabs.setupWithViewPager(pager);
        // secret hygiene on tab switch: re-blur whatever the leaving tab had on show
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override public void onPageSelected(int position) {
                diceView.hideWords();
                lastWordView.hideAll();
            }
        });

        if (selfTestErr != null) {
            TextView banner = findViewById(R.id.pairingBanner);
            banner.setText("SELF-TEST FAILED — generation blocked: " + selfTestErr);
            banner.setVisibility(android.view.View.VISIBLE);
            diceView.setSourceLine("self-test FAILED — blocked", false);
        } else {
            diceView.setSourceLine("BIP39 self-test ✓ — node: connecting…", true);
        }

        // never leave rolls gated forever if the node app is absent
        main.postDelayed(() -> {
            if (!engineReady) {
                engineReady = true;
                engine.setNodeOk(false);
                if (selfTestErr == null)
                    diceView.setSourceLine("node unavailable — device CSPRNG only (single source)", false);
            }
        }, 5000);
    }

    /** Prove the node answers `random` and `hash` with well-formed values before any roll uses it. */
    private void verifyNode() {
        node.cmd("random", new NodeApi.Cb() {
            @Override public void onResult(JSONObject r) {
                String a = r != null && r.optJSONObject("response") != null
                        ? r.optJSONObject("response").optString("random", "") : "";
                if (!a.startsWith("0x")) { failVerify(); return; }
                node.cmd("hash data:" + a, new NodeApi.Cb() {
                    @Override public void onResult(JSONObject h) {
                        String hash = h != null && h.optJSONObject("response") != null
                                ? h.optJSONObject("response").optString("hash", "") : "";
                        boolean ok = hash.startsWith("0x") && hash.length() >= 10;
                        engine.setNodeOk(ok);
                        engineReady = true;
                        if (selfTestErr == null)
                            diceView.setSourceLine(ok
                                    ? "BIP39 self-test ✓ — node SHA3 + device CSPRNG verified"
                                    : "node hash malformed — device CSPRNG only (single source)", ok);
                    }
                    @Override public void onError(String m) { failVerify(); }
                });
            }
            @Override public void onError(String m) { failVerify(); }
        });
    }

    private void failVerify() {
        engine.setNodeOk(false);
        engineReady = true;
        if (selfTestErr == null)
            diceView.setSourceLine("node commands failed — device CSPRNG only (single source)", false);
    }

    @Override
    protected void onDestroy() {
        main.removeCallbacksAndMessages(null);   // pending engine-ready guard
        // wipe secrets from the UI before teardown
        if (diceView != null) diceView.wipe();
        if (lastWordView != null) lastWordView.clearAll();
        if (node != null) node.onDestroy();
        Sfx.release();
        super.onDestroy();
    }
}
