package svg.themer

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import org.jetbrains.skia.Data
import org.jetbrains.skia.Surface
import org.jetbrains.skia.svg.SVGDOM
import java.io.ByteArrayOutputStream

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
    val bmp = bitmap.asAndroidBitmap()
    val out = ByteArrayOutputStream()
    bmp.compress(Bitmap.CompressFormat.PNG, 100, out)
    return out.toByteArray()
}
