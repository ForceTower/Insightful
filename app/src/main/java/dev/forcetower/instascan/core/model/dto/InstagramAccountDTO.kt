package dev.forcetower.instascan.core.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InstagramAccountDTO(
    val id: String,
    val igId: Long,
    val name: String,
    val username: String,
    val biography: String?,
    val profilePictureUrl: String?,
    val followersCount: Int,
    val followsCount: Int
) : Parcelable