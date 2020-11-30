package dev.forcetower.instascan.core.model.storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity
data class Story(
    @PrimaryKey
    val id: String,
    val igId: String,
    val mediaType: String,
    val mediaUrl: String,
    val timestamp: ZonedDateTime,
    val permalink: String
)