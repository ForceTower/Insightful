package dev.forcetower.instascan.view.home

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class HomeFeature(
    val title: String,
    val subtitle: String,
    @DrawableRes val icon: Int,
    @ColorRes val color: Int
)