package dev.forcetower.instascan.core.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import dev.forcetower.instascan.core.model.storage.Account
import dev.forcetower.instascan.core.model.storage.AccountAccess
import dev.forcetower.toolkit.database.dao.BaseDao

@Dao
abstract class AccountAccessDao : BaseDao<AccountAccess>() {
    @Query("SELECT accessToken FROM AccountAccess WHERE selected = 1")
    abstract fun getCurrentAccessTokenDirect(): String?

    @Query("SELECT accessToken FROM AccountAccess WHERE selected = 1")
    abstract suspend fun getCurrentAccessToken(): String?

    @Query("SELECT * FROM AccountAccess WHERE id = :id")
    abstract suspend fun getAccountAccess(id: String): AccountAccess?

    @Query("SELECT * FROM AccountAccess WHERE selected = 1")
    abstract suspend fun getCurrentAccountAccess(): AccountAccess?

    @Query("SELECT * FROM AccountAccess")
    abstract suspend fun getAll(): List<AccountAccess>

    @Query("SELECT accessToken FROM AccountAccess")
    abstract suspend fun getAllIds(): List<String>
}