package dev.forcetower.instascan.core.model.storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity
data class Media(
    @PrimaryKey
    val id: String,
    val igId: String,
    val commentsCount: Int,
    val likeCount: Int,
    val mediaType: String,
    val mediaUrl: String,
    val caption: String?,
    val timestamp: ZonedDateTime,
    val permalink: String
)