package dev.forcetower.instascan.view.home

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["srcRes", "tintRes"], requireAll = true)
fun imageViewResources(iv: ImageView, @DrawableRes src: Int?, @ColorRes tint: Int?) {
    src ?: return

    val context = iv.context
    iv.setImageDrawable(ContextCompat.getDrawable(context, src))
    tint?.let {
        iv.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, it))
    }
}