# Graph Report - apks/entropy  (2026-09-04)

## Corpus Check
- 22 files · ~17,835 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 319 nodes · 778 edges · 16 communities (14 shown, 2 thin omitted)
- Extraction: 96% EXTRACTED · 4% INFERRED · 0% AMBIGUOUS · INFERRED: 34 edges (avg confidence: 0.83)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `fd144279`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- .build
- Sfx
- MainActivity
- Weak-PRNG Wallet-drain Failure Class (small enumerable seed space)
- Bip39
- DiceView
- LastWordView
- DiceRollView
- BaseView
- Entropy RNG Audit
- gradlew
- install.sh
- pre-commit

## God Nodes (most connected - your core abstractions)
1. `DiceView` - 30 edges
2. `LastWordView` - 30 edges
3. `Sfx` - 29 edges
4. `Theme` - 24 edges
5. `DiceRollView` - 19 edges
6. `MainActivity` - 19 edges
7. `Bip39` - 18 edges
8. `RollEngine` - 18 edges
9. `NodeApi` - 15 edges
10. `BaseView` - 13 edges

## Surprising Connections (you probably didn't know these)
- `Roll Formula: SHA3(node random ++ Android SecureRandom) → first 4 bytes mod 6, rejection-sampled` --semantically_similar_to--> `Die-face Pipeline (RollEngine.java, a→b→h→n→face)`  [INFERRED] [semantically similar]
  apks/entropy/README.md → apks/entropy/SECURITY-REVIEW.md
- `Broadcast IPC to Minima Core (org.minimarex.minimacore)` --semantically_similar_to--> `Package-targeted Broadcast IPC (intent.setPackage both directions)`  [INFERRED] [semantically similar]
  apks/entropy/README.md → apks/entropy/SECURITY-REVIEW.md
- `BIP39 Parity Fixed Vectors (Bip39Test.java)` --semantically_similar_to--> `iancoleman Unbiased Dice Encoding (faces→bits→words, self-test gated)`  [INFERRED] [semantically similar]
  apks/entropy/README.md → apks/entropy/SECURITY-REVIEW.md
- `Entropy RNG Audit (published HTML page)` --semantically_similar_to--> `Entropy RNG Audit (Security Review 2026-08-17, entropy 0.1.8)`  [INFERRED] [semantically similar]
  apks/entropy/docs/index.html → apks/entropy/SECURITY-REVIEW.md
- `BaseView` --references--> `MainActivity`  [EXTRACTED]
  apks/entropy/app/src/main/java/com/eurobuddha/entropy/BaseView.java → apks/entropy/app/src/main/java/com/eurobuddha/entropy/MainActivity.java

## Import Cycles
- None detected.

## Hyperedges (group relationships)
- **Provably Fair Roll Pipeline (dual CSPRNG → SHA3 → rejection sampling → iancoleman encoding)** — apks_entropy_security_review_roll_pipeline, apks_entropy_security_review_dual_entropy_sources, apks_entropy_security_review_sha3_onchain_verifiability, apks_entropy_security_review_rejection_sampling, apks_entropy_security_review_iancoleman_encoding [EXTRACTED 1.00]
- **Drained-wallet Weak-PRNG Incident Class** — apks_entropy_security_review_milk_sad_incident, apks_entropy_security_review_trust_wallet_incident, apks_entropy_security_review_android_securerandom_bug, apks_entropy_security_review_cake_wallet_incident, apks_entropy_security_review_weak_prng_failure_class [EXTRACTED 1.00]
- **Entropy 0.1.8 Same-day Hardening Wave (three residual risks fixed)** — apks_entropy_security_review_node_seed_reconstruction_risk, apks_entropy_security_review_demo_mode_risk, apks_entropy_security_review_ipc_token_weak_prng_risk, apks_entropy_security_review_018_hardening [EXTRACTED 1.00]

## Communities (16 total, 2 thin omitted)

### Community 0 - ".build"
Cohesion: 0.11
Nodes (15): android.content.Context, android.graphics.drawable.GradientDrawable, android.graphics.Typeface, android.widget.LinearLayout, android.widget.TextView, Theme, Button, LinearLayout (+7 more)

### Community 1 - "Sfx"
Cohesion: 0.13
Nodes (12): android.media.AudioTrack, Filter, BANDPASS, HIGHPASS, LOWPASS, Render, Sfx, Wave (+4 more)

### Community 2 - "MainActivity"
Cohesion: 0.07
Nodes (16): android.os.Bundle, android.os.Handler, androidx.appcompat.app.AppCompatActivity, Override, MainActivity, Cb, NodeApi, PairingListener (+8 more)

### Community 3 - "Weak-PRNG Wallet-drain Failure Class (small enumerable seed space)"
Cohesion: 0.08
Nodes (31): Pre-commit Version-bump Hook (.githooks/pre-commit), Versioning Guardrail — every code change ships with a version bump, Follow-up: Family Apps Still Vendor the Pre-fix minimaapi.aar, Entropy RNG Audit (published HTML page), BIP39 Parity Fixed Vectors (Bip39Test.java), Broadcast IPC to Minima Core (org.minimarex.minimacore), DICE ROLL Tab (12/15/18/21/24 words, digital or physical dice, per-roll audit log), Entropy — Provably Fair BIP39 Seed Dice APK (+23 more)

### Community 4 - "Bip39"
Cohesion: 0.14
Nodes (5): Bip39, LastWords, Bip39Test, org.junit.BeforeClass, org.junit.Test

### Community 5 - "DiceView"
Cohesion: 0.29
Nodes (4): android.widget.Button, android.widget.ProgressBar, DiceView, Override

### Community 6 - "LastWordView"
Cohesion: 0.18
Nodes (4): android.widget.EditText, android.widget.HorizontalScrollView, Override, LastWordView

### Community 7 - "DiceRollView"
Cohesion: 0.19
Nodes (7): android.animation.ValueAnimator, android.graphics.Canvas, android.graphics.Paint, android.graphics.RectF, android.util.AttributeSet, DiceRollView, Override

### Community 8 - "BaseView"
Cohesion: 0.17
Nodes (7): android.view.View, android.view.ViewGroup, androidx.annotation.NonNull, androidx.viewpager.widget.PagerAdapter, BaseView, Override, MainPager

### Community 10 - "Entropy RNG Audit"
Cohesion: 0.12
Nodes (15): Build, Entropy — native Android APK, Install, Release, 1. How one die face is made, 2. Why the known wallet-RNG hacks don't apply, 3. What the review verified, 4. Residual risks — and the hardening shipped (+7 more)

### Community 11 - "gradlew"
Cohesion: 0.60
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **26 isolated node(s):** `install.sh script`, `SINE`, `SQUARE`, `SAW`, `HIGHPASS` (+21 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **2 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `DiceView` connect `DiceView` to `.build`, `MainActivity`, `Bip39`, `DiceRollView`, `BaseView`?**
  _High betweenness centrality (0.102) - this node is a cross-community bridge._
- **Why does `Sfx` connect `Sfx` to `MainActivity`?**
  _High betweenness centrality (0.096) - this node is a cross-community bridge._
- **Why does `LastWordView` connect `LastWordView` to `.build`, `BaseView`, `MainActivity`, `DiceView`?**
  _High betweenness centrality (0.086) - this node is a cross-community bridge._
- **What connects `install.sh script`, `SINE`, `SQUARE` to the rest of the system?**
  _26 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `.build` be split into smaller, more focused modules?**
  _Cohesion score 0.10974025974025974 - nodes in this community are weakly interconnected._
- **Should `Sfx` be split into smaller, more focused modules?**
  _Cohesion score 0.12612612612612611 - nodes in this community are weakly interconnected._
- **Should `MainActivity` be split into smaller, more focused modules?**
  _Cohesion score 0.06531986531986532 - nodes in this community are weakly interconnected._