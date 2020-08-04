package dev.forcetower.instascan.core.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import dev.forcetower.instascan.core.model.storage.Account
import dev.forcetower.toolkit.database.dao.BaseDao

@Dao
abstract class AccountDao : BaseDao<Account>() {
    @Suppress("FunctionName")
    @Query("SELECT accessToken FROM Account WHERE selected = 1")
    abstract fun UNSAFE_getCurrentAccountToken(): String?
}