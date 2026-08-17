# Graph Report - entropy  (2026-08-17)

## Corpus Check
- 21 files · ~14,476 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 274 nodes · 717 edges · 16 communities (13 shown, 3 thin omitted)
- Extraction: 97% EXTRACTED · 3% INFERRED · 0% AMBIGUOUS · INFERRED: 23 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `c69e8ee3`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- RollEngine
- android.content.Context
- Sfx
- Bip39
- .build
- LastWordView
- DiceRollView
- BaseView
- gradlew
- install.sh
- pre-commit
- MainActivity
- Override

## God Nodes (most connected - your core abstractions)
1. `Sfx` - 29 edges
2. `LastWordView` - 28 edges
3. `DiceView` - 27 edges
4. `Theme` - 24 edges
5. `DiceRollView` - 19 edges
6. `MainActivity` - 18 edges
7. `Bip39` - 18 edges
8. `RollEngine` - 15 edges
9. `BaseView` - 13 edges
10. `NodeApi` - 13 edges

## Surprising Connections (you probably didn't know these)
- `BaseView` --references--> `MainActivity`  [EXTRACTED]
  app/src/main/java/com/eurobuddha/entropy/BaseView.java → app/src/main/java/com/eurobuddha/entropy/MainActivity.java
- `DiceView` --inherits--> `BaseView`  [EXTRACTED]
  app/src/main/java/com/eurobuddha/entropy/DiceView.java → app/src/main/java/com/eurobuddha/entropy/BaseView.java
- `LastWordView` --inherits--> `BaseView`  [EXTRACTED]
  app/src/main/java/com/eurobuddha/entropy/LastWordView.java → app/src/main/java/com/eurobuddha/entropy/BaseView.java
- `DiceView` --references--> `DiceRollView`  [EXTRACTED]
  app/src/main/java/com/eurobuddha/entropy/DiceView.java → app/src/main/java/com/eurobuddha/entropy/DiceRollView.java
- `MainPager` --references--> `BaseView`  [EXTRACTED]
  app/src/main/java/com/eurobuddha/entropy/MainPager.java → app/src/main/java/com/eurobuddha/entropy/BaseView.java

## Import Cycles
- None detected.

## Communities (16 total, 3 thin omitted)

### Community 0 - "RollEngine"
Cohesion: 0.11
Nodes (11): android.os.Handler, Cb, NodeApi, PairingListener, NCb, RollCb, RollEngine, java.security.SecureRandom (+3 more)

### Community 1 - "android.content.Context"
Cohesion: 0.16
Nodes (13): android.content.Context, android.graphics.drawable.GradientDrawable, android.graphics.Typeface, android.widget.Button, android.widget.LinearLayout, android.widget.TextView, Button, LinearLayout (+5 more)

### Community 2 - "Sfx"
Cohesion: 0.13
Nodes (12): android.media.AudioTrack, Filter, BANDPASS, HIGHPASS, LOWPASS, Render, Sfx, Wave (+4 more)

### Community 3 - "Bip39"
Cohesion: 0.15
Nodes (5): Bip39, LastWords, Bip39Test, org.junit.BeforeClass, org.junit.Test

### Community 4 - ".build"
Cohesion: 0.13
Nodes (6): android.widget.ProgressBar, DiceView, Override, Audit, Theme, ProgressBar

### Community 5 - "LastWordView"
Cohesion: 0.17
Nodes (5): android.widget.EditText, android.widget.HorizontalScrollView, Override, LastWordView, EditText

### Community 6 - "DiceRollView"
Cohesion: 0.20
Nodes (7): android.animation.ValueAnimator, android.graphics.Canvas, android.graphics.Paint, android.graphics.RectF, android.util.AttributeSet, DiceRollView, Override

### Community 7 - "BaseView"
Cohesion: 0.19
Nodes (7): android.view.View, android.view.ViewGroup, androidx.annotation.NonNull, androidx.viewpager.widget.PagerAdapter, BaseView, Override, MainPager

### Community 8 - "gradlew"
Cohesion: 0.60
Nodes (3): gradlew script, die(), warn()

### Community 14 - "MainActivity"
Cohesion: 0.11
Nodes (8): android.os.Bundle, androidx.appcompat.app.AppCompatActivity, MainActivity, DiceView, LastWordView, NodeApi, Override, RollEngine

## Knowledge Gaps
- **7 isolated node(s):** `install.sh script`, `SINE`, `SQUARE`, `SAW`, `HIGHPASS` (+2 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **3 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `Sfx` connect `Sfx` to `MainActivity`?**
  _High betweenness centrality (0.134) - this node is a cross-community bridge._
- **Why does `DiceView` connect `.build` to `RollEngine`, `android.content.Context`, `DiceRollView`, `BaseView`, `MainActivity`?**
  _High betweenness centrality (0.133) - this node is a cross-community bridge._
- **Why does `LastWordView` connect `LastWordView` to `android.content.Context`, `.build`, `MainActivity`, `BaseView`?**
  _High betweenness centrality (0.090) - this node is a cross-community bridge._
- **What connects `install.sh script`, `SINE`, `SQUARE` to the rest of the system?**
  _7 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `RollEngine` be split into smaller, more focused modules?**
  _Cohesion score 0.11174242424242424 - nodes in this community are weakly interconnected._
- **Should `Sfx` be split into smaller, more focused modules?**
  _Cohesion score 0.12612612612612611 - nodes in this community are weakly interconnected._
- **Should `.build` be split into smaller, more focused modules?**
  _Cohesion score 0.13086770981507823 - nodes in this community are weakly interconnected._