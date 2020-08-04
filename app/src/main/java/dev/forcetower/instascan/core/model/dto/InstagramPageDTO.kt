package dev.forcetower.instascan.core.model.dto

data class InstagramPageDTO(
    val id: String,
    val name: String,
    val instagramBusinessAccount: InstagramAccountDTO?
)