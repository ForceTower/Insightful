package dev.forcetower.instascan.core.model.storage

import androidx.room.PrimaryKey

data class StoryInsight(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val reach: Int,
    val exits: Int
)