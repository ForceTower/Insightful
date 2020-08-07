package dev.forcetower.instascan.widget.blurbottomnav

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.forcetower.toolkit.extensions.getPixelsFromDp
import dev.forcetower.toolkit.utils.FastBlur

class BlurBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {

    override fun getBackground(): Drawable {
        val drawable =  super.getBackground()
        val metrics = context.resources.displayMetrics
        val bitmap = FastBlur.blur(context, drawable.toBitmap(metrics.widthPixels, context.getPixelsFromDp(56).toInt()), 25f)
        return bitmap.toDrawable(context.resources)
    }
}