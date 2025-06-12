# svg-themer

A lightweight Kotlin Multiplatform library for displaying SVG images that adapt to the current theme.

## Features

- Works with JetBrains Compose Multiplatform (Android & iOS).
- Replaces color tokens in an SVG string at runtime.
- Provides a composable `rememberThemedSvgPainter` for easy use in Compose UI.
- Plain API to rasterize SVG text to `ImageBitmap` or PNG bytes.
- Includes a small Compose Desktop sample in [`sample/`](sample) demonstrating usage.

## Usage

SVG files can contain placeholders using `{{token}}` syntax. Provide colors for light and dark themes via `TokenColors`:

```kotlin
val tokens = TokenColors(
    light = mapOf("primary" to Color(0xFF6200EE)),
    dark = mapOf("primary" to Color(0xFFBB86FC))
)
```

In Compose:

```kotlin
Image(
    painter = rememberThemedSvgPainter(svgText, tokens),
    contentDescription = null
)
```

For non‑UI usage:

```kotlin
val bitmap: ImageBitmap = rasterizeSvgToImageBitmap(svgText)
val png: ByteArray = rasterizeSvgToPngBytes(svgText)
```

All color substitution is performed directly on the SVG text before parsing so the runtime footprint stays small.

## Sample project

Run the Compose Desktop demo with:

```bash
gradle :sample:run
```
