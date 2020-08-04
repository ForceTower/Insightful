package dev.forcetower.instascan.core.model.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountAccess(
    @PrimaryKey
    val id: String,
    val igId: Long,
    val name: String,
    val accessToken: String,
    val username: String,
    val biography: String?,
    val profilePictureUrl: String?,
    val followersCount: Int,
    val followsCount: Int,
    val selected: Boolean
)