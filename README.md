# Entropy — native Android APK

Provably fair BIP39 seed dice for Minima, native Android port of the [Entropy MiniDapp](https://github.com/eurobuddha/entropy).

- Rolls: `SHA3(node random ++ Android SecureRandom)` → first 4 bytes mod 6, rejection-sampled (zero bias). Node access is broadcast IPC to the separately installed Minima Core app (`org.minimarex.minimacore`) — no embedded node, **zero permissions**, FLAG_SECURE (no screenshots), nothing persisted.
- Maths locked to iancoleman/bip39 parity by fixed vectors (`app/src/test/.../Bip39Test.java`) produced from the genuine code via the MiniDapp harness.
- Tabs: DICE ROLL (12/15/18/21/24 words, digital or physical dice, per-roll audit log) and LAST WORD (checksum calculator with 4-char autocomplete).

## Build

```
./gradlew assembleRelease     # needs the Android Studio JBR (pinned in gradle.properties)
```

## Install

Install Minima Core on the device → sideload `releases/Entropy-<ver>.apk` → Minima Core → Apps → enable **Entropy**.

## Release

```
../../support/release.sh entropy minima-core-android-entropy <version> "<summary>"
```
