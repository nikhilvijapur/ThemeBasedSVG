package svg.themer

import androidx.compose.ui.graphics.Color

/**
 * Maps placeholder tokens in an SVG string to [Color] values for light and dark themes.
 */
data class TokenColors(
    val light: Map<String, Color>,
    val dark: Map<String, Color>
)

/**
 * Replace placeholder tokens in the provided SVG [text] using [colors].
 * Placeholders use the form `{{token}}`.
 */
fun applyColorTokens(text: String, colors: Map<String, Color>): String {
    var out = text
    for ((token, color) in colors) {
        val hex = color.toHexString()
        out = out.replace("{{$token}}", hex, ignoreCase = true)
    }
    return out
}

private fun Color.toHexString(): String {
    val a = (alpha * 255).toInt().toString(16).padStart(2, '0')
    val r = (red * 255).toInt().toString(16).padStart(2, '0')
    val g = (green * 255).toInt().toString(16).padStart(2, '0')
    val b = (blue * 255).toInt().toString(16).padStart(2, '0')
    return "#$r$g$b$a"
}
