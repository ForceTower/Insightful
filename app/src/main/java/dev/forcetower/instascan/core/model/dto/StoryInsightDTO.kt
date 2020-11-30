package dev.forcetower.instascan.core.model.dto

data class StoryInsightDTO(
    val name: String,
    val period: String,
    val values: List<ValueDTO>
) {
    fun asStoryInsight(id: String) {

    }
}