# Graph Report - entropy  (2026-08-17)

## Corpus Check
- 21 files · ~14,597 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 279 nodes · 722 edges · 17 communities (13 shown, 4 thin omitted)
- Extraction: 97% EXTRACTED · 3% INFERRED · 0% AMBIGUOUS · INFERRED: 23 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `b003c61b`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- RollEngine
- .build
- Sfx
- Bip39
- DiceView
- LastWordView
- DiceRollView
- android.view.View
- gradlew
- install.sh
- pre-commit
- MainActivity
- DiceView
- Override

## God Nodes (most connected - your core abstractions)
1. `DiceView` - 30 edges
2. `Sfx` - 29 edges
3. `LastWordView` - 28 edges
4. `Theme` - 24 edges
5. `MainActivity` - 19 edges
6. `Bip39` - 18 edges
7. `DiceRollView` - 17 edges
8. `RollEngine` - 15 edges
9. `NodeApi` - 13 edges
10. `Ui` - 13 edges

## Surprising Connections (you probably didn't know these)
- `MainActivity` --references--> `DiceView`  [EXTRACTED]
  app/src/main/java/com/eurobuddha/entropy/MainActivity.java → app/src/main/java/com/eurobuddha/entropy/DiceView.java
- `BaseView` --references--> `MainActivity`  [EXTRACTED]
  app/src/main/java/com/eurobuddha/entropy/BaseView.java → app/src/main/java/com/eurobuddha/entropy/MainActivity.java
- `LastWordView` --inherits--> `BaseView`  [EXTRACTED]
  app/src/main/java/com/eurobuddha/entropy/LastWordView.java → app/src/main/java/com/eurobuddha/entropy/BaseView.java
- `MainPager` --references--> `BaseView`  [EXTRACTED]
  app/src/main/java/com/eurobuddha/entropy/MainPager.java → app/src/main/java/com/eurobuddha/entropy/BaseView.java
- `RollEngine` --references--> `NodeApi`  [EXTRACTED]
  app/src/main/java/com/eurobuddha/entropy/RollEngine.java → app/src/main/java/com/eurobuddha/entropy/NodeApi.java

## Import Cycles
- None detected.

## Communities (17 total, 4 thin omitted)

### Community 0 - "RollEngine"
Cohesion: 0.11
Nodes (12): android.os.Handler, Cb, NodeApi, PairingListener, Audit, NCb, RollCb, RollEngine (+4 more)

### Community 1 - ".build"
Cohesion: 0.12
Nodes (12): android.content.Context, android.graphics.Typeface, Theme, Button, LinearLayout, TextView, Ui, EditText (+4 more)

### Community 2 - "Sfx"
Cohesion: 0.13
Nodes (12): android.media.AudioTrack, Filter, BANDPASS, HIGHPASS, LOWPASS, Render, Sfx, Wave (+4 more)

### Community 3 - "Bip39"
Cohesion: 0.16
Nodes (5): Bip39, LastWords, Bip39Test, org.junit.BeforeClass, org.junit.Test

### Community 4 - "DiceView"
Cohesion: 0.27
Nodes (5): DiceView, Override, Audit, BaseView, DiceRollView

### Community 5 - "LastWordView"
Cohesion: 0.15
Nodes (9): android.graphics.drawable.GradientDrawable, android.widget.Button, android.widget.EditText, android.widget.HorizontalScrollView, android.widget.LinearLayout, android.widget.ProgressBar, android.widget.TextView, Override (+1 more)

### Community 6 - "DiceRollView"
Cohesion: 0.20
Nodes (7): android.animation.ValueAnimator, android.graphics.Canvas, android.graphics.Paint, android.graphics.RectF, android.util.AttributeSet, DiceRollView, Override

### Community 7 - "android.view.View"
Cohesion: 0.17
Nodes (7): android.view.View, android.view.ViewGroup, androidx.annotation.NonNull, androidx.viewpager.widget.PagerAdapter, BaseView, Override, MainPager

### Community 8 - "gradlew"
Cohesion: 0.60
Nodes (3): gradlew script, die(), warn()

### Community 14 - "MainActivity"
Cohesion: 0.10
Nodes (7): android.os.Bundle, androidx.appcompat.app.AppCompatActivity, Override, MainActivity, LastWordView, NodeApi, RollEngine

## Knowledge Gaps
- **7 isolated node(s):** `install.sh script`, `SINE`, `SQUARE`, `SAW`, `HIGHPASS` (+2 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **4 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `Sfx` connect `Sfx` to `MainActivity`?**
  _High betweenness centrality (0.133) - this node is a cross-community bridge._
- **Why does `DiceView` connect `DiceView` to `RollEngine`, `.build`, `LastWordView`, `MainActivity`?**
  _High betweenness centrality (0.103) - this node is a cross-community bridge._
- **Why does `LastWordView` connect `LastWordView` to `.build`, `MainActivity`, `android.view.View`?**
  _High betweenness centrality (0.091) - this node is a cross-community bridge._
- **What connects `install.sh script`, `SINE`, `SQUARE` to the rest of the system?**
  _7 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `RollEngine` be split into smaller, more focused modules?**
  _Cohesion score 0.11051693404634581 - nodes in this community are weakly interconnected._
- **Should `.build` be split into smaller, more focused modules?**
  _Cohesion score 0.116701607267645 - nodes in this community are weakly interconnected._
- **Should `Sfx` be split into smaller, more focused modules?**
  _Cohesion score 0.12612612612612611 - nodes in this community are weakly interconnected._