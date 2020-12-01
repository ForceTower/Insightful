package dev.forcetower.instascan.view.home.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.res.getStringOrThrow
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import dev.forcetower.instascan.R
import dev.forcetower.toolkit.extensions.getPixelsFromDp

class FeatureView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {
    private val root = LayoutInflater.from(context).inflate(R.layout.view_home_feature, this, true)
    private val icon = root.findViewById<AppCompatImageView>(R.id.icon)
    private val title = root.findViewById<MaterialTextView>(R.id.title)
    private val subtitle = root.findViewById<MaterialTextView>(R.id.subtitle)

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.FeatureView,
            defStyleAttr,
            0
        )

        val title = typedArray.getStringOrThrow(R.styleable.FeatureView_title)
        val subtitle = typedArray.getStringOrThrow(R.styleable.FeatureView_subtitle)
        val icon = typedArray.getResourceIdOrThrow(R.styleable.FeatureView_iconSrc)
        val color = typedArray.getResourceIdOrThrow(R.styleable.FeatureView_iconColor)

        typedArray.recycle()

        setTitle(title)
        setSubtitle(subtitle)
        setIconAndTint(icon, color)
        radius = context.getPixelsFromDp(8)
        cardElevation = context.getPixelsFromDp(4)
    }

    private fun setIconAndTint(@DrawableRes src: Int?, @ColorRes color: Int?) {
        src?.let { icon.setImageDrawable(ContextCompat.getDrawable(context, src)) }
        color?.let { icon.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color)) }
    }

    private fun setTitle(text: String) {
        title.text = text
    }

    private fun setSubtitle(text: String) {
        subtitle.text = text
    }
}