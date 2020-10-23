package dev.forcetower.instascan.core.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import dev.forcetower.instascan.core.model.storage.Media
import dev.forcetower.toolkit.database.dao.BaseDao

@Dao
abstract class MediaDao : BaseDao<Media>() {
    @Query("SELECT * FROM Media WHERE id = :id")
    abstract suspend fun getByIDDirect(id: String): Media?

    override suspend fun getValueByIDDirect(value: Media): Media? {
        return getByIDDirect(value.id)
    }
}