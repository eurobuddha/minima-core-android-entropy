# Entropy RNG Audit

**Security review · 2026-08-17 · entropy 0.1.8**

Are the seed-phrase dice rolls truly random, or crackable? A full-path review of the roll
engine, BIP39 encoding, and IPC transport in the Entropy native Android app for Minima.

> **Verdict: cryptographically sound — not crackable in any practical sense.**
> Every roll mixes a fresh 256-bit kernel-CSPRNG secret with independent node randomness
> through SHA3, with exact rejection sampling. An attacker faces 2¹²⁸–2²⁵⁶ uniform
> possibilities and no computational shortcut. The weak-PRNG failure class behind the
> wallet-drain hacks does not exist in this design.

## 1. How one die face is made

`RollEngine.java` — ported from the Zero Edge Casino resolve maths, proven on-chain.

| step | what happens | why it matters |
|------|--------------|----------------|
| `a` | The Minima node supplies fresh randomness via its `random` command | First independent CSPRNG; travels over package-targeted IPC only |
| `b` | The device draws a fresh 32-byte secret from `SecureRandom` — per roll | Second independent source: the Android kernel CSPRNG. 256 bits nobody else ever sees in advance |
| `h` | The node computes `SHA3(a ++ b)` — bit-identical to the KISS VM script, so any roll can be re-verified with `runscript` in a Minima terminal | Either source alone failing still leaves the output unpredictable |
| `n` | The first 4 bytes of the hash become a 32-bit number; values ≥ `4294967292` are rerolled | 4294967292 = floor(2³²⁄6)·6 — rejection sampling gives every face exactly 1⁄6. Zero modulo bias, pinned by a unit test |
| ⚅ | `n mod 6 + 1` is the face. Faces become entropy bits via the iancoleman unbiased encoding | `Bip39Test` verifies parity with iancoleman/bip39 v0.4.3; generation is blocked if the self-test fails |

## 2. Why the known wallet-RNG hacks don't apply

Every drained-wallet RNG incident shared one root cause: a small, enumerable seed space.

| incident | root cause | search space |
|----------|-----------|--------------|
| Milk Sad (Libbitcoin `bx seed`, 2023) | Mersenne Twister seeded with 32-bit time | 2³² |
| Trust Wallet browser extension (2023) | MT19937, 32-bit seed | 2³² |
| Android Bitcoin wallets (2013) | Broken `SecureRandom` duplicated values (fixed in Android 4.4+) | tiny |
| Cake Wallet (2020, disclosed 2021) | Dart's default non-crypto `Random` | 2³² |
| **Entropy** | **Fresh 256-bit kernel-CSPRNG secret per roll + second source, hashed** | **2¹²⁸–2²⁵⁶** |

Those wallets asked a non-cryptographic generator, seeded with a handful of guessable bits,
to invent the whole seed — so attackers simply enumerated every possible seed and swept the
matching addresses. Entropy has no such shortcut: even an adversary who fully controls the
node's contribution still faces the 256-bit device secret behind SHA3.

## 3. What the review verified

- ✅ Fresh 256-bit `SecureRandom` secret drawn per roll, never reused —
  `app/src/main/java/com/eurobuddha/entropy/RollEngine.java:57`
- ✅ Exact rejection sampling — threshold 4294967292, error out after 25 rejects rather than
  degrade — `RollEngine.java:24`, `Bip39Test.rejectionThresholdIsExact`
- ✅ Outbound IPC is an **explicit broadcast to Minima Core only** — third-party apps cannot
  receive it — `minimaapi/.../MinimaAPI.java` (`intent.setPackage`)
- ✅ Node replies are **explicitly targeted back at this app only** — no on-device
  eavesdropping of the hash results — node-side `MinimaReceiver.java`
- ✅ Faces→bits→words is the iancoleman unbiased dice encoding with raw truncation, enforced
  by fixed vectors; a failed self-test blocks generation entirely — `Bip39.selfTest()`
- ✅ Nothing persisted, zero Android permissions, FLAG_SECURE against screenshots; words
  re-blur on tab switch and app teardown wipes the UI — `MainActivity.java`, `DiceView.wipe()`

## 4. Residual risks — and the hardening shipped

None is a practical crack; all assume a compromised device or misused demo mode. All three
were addressed the same day.

### The node can reconstruct the seed
The SHA3 runs on the node, so Minima Core sees both secrets and hence every face. This is the
deliberate price of on-chain verifiability — and if Minima Core or the phone is compromised,
any on-device generator has already lost. The per-roll audit log is equally seed-equivalent.
> ✓ 0.1.8 adds a warning above the audit log: treat it exactly like the seed words.

### Demo mode lifts FLAG_SECURE
Demo mode (0.1.7) makes the screen capturable for presentations. Faces captured on a mirrored
or recorded screen are seed material.
> ✓ 0.1.8 blocks all rolls — digital and physical — while demo mode is on, and re-blurs any
> revealed words the moment it is switched on.

### IPC auth tokens came from java.util.Random
The tokens authenticating node replies to the app's exported receiver were minted by a
48-bit-seeded, non-cryptographic PRNG. Blind forgery over broadcasts was already infeasible,
but the barrier was thinner than it should be.
> ✓ The shared minimaapi SDK now mints all IPC tokens with SecureRandom; the rebuilt library
> ships inside 0.1.8.

### Commit ledger

| change | commit |
|--------|--------|
| entropy 0.1.8 — security hardening | `b003c61bc76b9a8d5a86318a86e981746b01a519` |
| minimaapi SDK — SecureRandom IPC tokens | `eff38d03cf13ebaea445596dac65133b34c3b558` |
| entropy 0.1.7 — demo mode | `c69e8ee3a8c55c77b54253fe52733f8b4a18ea81` |

Published release:
https://github.com/eurobuddha/minima-core-apks/releases/tag/minima-core-android-entropy-v0.1.8

## 5. Digital rolls vs five physical dice

For every purpose that matters to a seed phrase: **yes — at least as random, and in one
respect better.** The guarantees differ in kind, not in practical strength.

**Digital wins: exactness.** Rejection sampling makes every face *exactly* 1⁄6 — no physical
die achieves that. Household dice have rounded corners, off-center pips, thrower habits —
measurable bias that shaves entropy per roll. The digital output is computationally
indistinguishable from perfect dice to any adversary who can't break SHA3 or the kernel CSPRNG.

**Physical wins: unconditional physics.** A fair die rolled unobserved is unpredictable by
physics alone — no cryptographic assumptions, and malware can't touch a die on the table.
But in this app the physical faces are typed into the same phone, so a compromised device
reads those too; physical mode only truly wins with the whole derivation done off-device.

On an uncompromised phone, the digital rolls are at least as strong as ideal dice and stronger
than typical real ones. The one scenario where physical dice genuinely win — a fully
compromised device — is one where any app has already lost, whichever mode is used.

## 6. Scope & method

Full-path source review conducted 2026-08-17 against entropy 0.1.7→0.1.8, the shared
minimaapi SDK, and the node-side broadcast receiver: `RollEngine.java`, `Bip39.java`,
`DiceView.java`, `MainActivity.java`, `NodeApi.java`, `MinimaAPI.java`, `MinimaReceiver.java`.
Unit tests (BIP39 parity vectors, rejection threshold) green before and after the hardening
commits.
