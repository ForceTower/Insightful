package dev.forcetower.instascan.core.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.forcetower.instascan.core.model.storage.Account
import dev.forcetower.instascan.core.model.storage.AccountAccess
import dev.forcetower.instascan.core.source.local.dao.AccountAccessDao
import dev.forcetower.instascan.core.source.local.dao.AccountDao

@Database(entities = [
    AccountAccess::class,
    Account::class
], version = 1)
abstract class InstrackDB : RoomDatabase() {
    abstract fun access(): AccountAccessDao
    abstract fun account(): AccountDao
}