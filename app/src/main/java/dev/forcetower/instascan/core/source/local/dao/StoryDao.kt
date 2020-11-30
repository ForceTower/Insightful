package dev.forcetower.instascan.core.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import dev.forcetower.instascan.core.model.storage.Media
import dev.forcetower.instascan.core.model.storage.Story
import dev.forcetower.toolkit.database.dao.BaseDao

@Dao
abstract class StoryDao : BaseDao<Story>() {
    @Query("SELECT * FROM Story WHERE id = :id")
    abstract suspend fun getByIDDirect(id: String): Story?

    override suspend fun getValueByIDDirect(value: Story): Story? {
        return getByIDDirect(value.id)
    }
}