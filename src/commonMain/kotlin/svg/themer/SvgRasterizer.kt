package svg.themer

import androidx.compose.ui.graphics.ImageBitmap

/** Platform implementation to convert an SVG string to [ImageBitmap]. */
expect fun rasterizeSvgToImageBitmap(svgText: String, width: Int? = null, height: Int? = null): ImageBitmap

/**
 * Rasterize an SVG and return PNG encoded bytes.
 */
fun rasterizeSvgToPngBytes(svgText: String, width: Int? = null, height: Int? = null): ByteArray {
    val bitmap = rasterizeSvgToImageBitmap(svgText, width, height)
    return encodeImageBitmapAsPng(bitmap)
}

expect fun encodeImageBitmapAsPng(bitmap: ImageBitmap): ByteArray
