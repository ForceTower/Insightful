package dev.forcetower.instascan.core.model.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey
    val id: String,
    val igId: Long,
    val name: String,
    val username: String,
    val biography: String?,
    val profilePictureUrl: String?,
    val followersCount: Int,
    val followsCount: Int
)