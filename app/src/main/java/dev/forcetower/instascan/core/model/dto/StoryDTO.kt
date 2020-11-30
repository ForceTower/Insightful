package dev.forcetower.instascan.core.model.dto

import dev.forcetower.instascan.core.model.storage.Story
import java.time.ZonedDateTime

data class StoryDTO(
    val id: String,
    val igId: String,
    val mediaType: String,
    val mediaUrl: String,
    val insights: BasicData<List<StoryInsightDTO>>,
    val timestamp: ZonedDateTime,
    val permalink: String
) {
    fun toStoryData(): Story {
        val story = Story(id, igId, mediaType, mediaUrl, timestamp, permalink)
        val insight = insights.data.map { it.asStoryInsight(story.id) }
        return story
    }
}