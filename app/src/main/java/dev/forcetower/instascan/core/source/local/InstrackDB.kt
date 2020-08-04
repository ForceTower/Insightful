package dev.forcetower.instascan.core.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.forcetower.instascan.core.model.storage.Account
import dev.forcetower.instascan.core.source.local.dao.AccountDao

@Database(entities = [
    Account::class
], version = 1)
abstract class InstrackDB : RoomDatabase() {
    abstract fun account(): AccountDao
}