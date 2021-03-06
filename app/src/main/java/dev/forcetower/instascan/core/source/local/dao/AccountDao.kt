package dev.forcetower.instascan.core.source.local.dao

import androidx.room.Dao
import dev.forcetower.instascan.core.model.storage.Account
import dev.forcetower.toolkit.database.dao.BaseDao

@Dao
abstract class AccountDao : BaseDao<Account>() {

}