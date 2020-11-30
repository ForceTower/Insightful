package dev.forcetower.instascan.core.model.dto

data class StoryResponse(
    val stories: CommonResponse<List<StoryDTO>>?
)