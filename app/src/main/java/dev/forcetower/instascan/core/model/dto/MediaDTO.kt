package dev.forcetower.instascan.core.model.dto

import dev.forcetower.instascan.core.model.storage.Media
import java.time.ZonedDateTime

data class MediaDTO(
    val id: String,
    val igId: String,
    val commentsCount: Int,
    val likeCount: Int,
    val mediaType: String,
    val mediaUrl: String,
    val caption: String?,
    val timestamp: ZonedDateTime,
    val permalink: String
) {
    fun toMedia() = Media(id, igId, commentsCount, likeCount, mediaType, mediaUrl, caption, timestamp, permalink)
}