package sample.main

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import svg.themer.TokenColors
import svg.themer.rememberThemedSvgPainter

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        SampleContent()
    }
}

@Composable
fun SampleContent() {
    val svgText = """
        <svg width="100" height="100">
            <rect width="100" height="100" fill="{{primary}}" />
        </svg>
    """.trimIndent()

    val tokens = TokenColors(
        light = mapOf("primary" to Color(0xFF6200EE)),
        dark = mapOf("primary" to Color(0xFFBB86FC))
    )

    Image(painter = rememberThemedSvgPainter(svgText, tokens), contentDescription = null)
}
