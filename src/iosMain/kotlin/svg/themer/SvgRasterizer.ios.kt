package svg.themer

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toImageBitmap
import org.jetbrains.skia.Data
import org.jetbrains.skia.Surface
import org.jetbrains.skia.svg.SVGDOM

actual fun rasterizeSvgToImageBitmap(svgText: String, width: Int?, height: Int?): ImageBitmap {
    val dom = SVGDOM(Data.makeFromBytes(svgText.encodeToByteArray()))
    val w = width ?: dom.containerSize.width.toInt()
    val h = height ?: dom.containerSize.height.toInt()
    dom.setContainerSize(w.toFloat(), h.toFloat())
    val surface = Surface.makeRasterN32Premul(w, h)
    val canvas = surface.canvas
    canvas.clear(0)
    dom.render(canvas)
    val snapshot = surface.makeImageSnapshot()
    return snapshot.toComposeImageBitmap()
}

actual fun encodeImageBitmapAsPng(bitmap: ImageBitmap): ByteArray {
    val skImage = bitmap.asSkiaImage()
    val data = skImage.encodeToData()
    return data?.bytes ?: ByteArray(0)
}
