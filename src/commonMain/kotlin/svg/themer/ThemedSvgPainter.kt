package svg.themer

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity

/**
 * Create a [Painter] for the provided SVG text with colors automatically
 * switched for the current theme.
 */
@Composable
fun rememberThemedSvgPainter(svgText: String, tokens: TokenColors): Painter {
    val dark = isSystemInDarkTheme()
    val colors = if (dark) tokens.dark else tokens.light
    val density = LocalDensity.current
    val processed = remember(svgText, colors) { applyColorTokens(svgText, colors) }
    val bitmap = remember(processed, density) { rasterizeSvgToImageBitmap(processed) }
    return remember(bitmap) { BitmapPainter(bitmap) }
}
