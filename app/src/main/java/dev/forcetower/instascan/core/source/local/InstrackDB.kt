package dev.forcetower.instascan.core.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.forcetower.instascan.core.model.storage.Account
import dev.forcetower.instascan.core.model.storage.AccountAccess
import dev.forcetower.instascan.core.model.storage.Media
import dev.forcetower.instascan.core.source.local.dao.AccountAccessDao
import dev.forcetower.instascan.core.source.local.dao.AccountDao
import dev.forcetower.instascan.core.source.local.dao.MediaDao

@Database(entities = [
    AccountAccess::class,
    Account::class,
    Media::class
], version = 1)
@TypeConverters(value = [DateConverters::class])
abstract class InstrackDB : RoomDatabase() {
    abstract fun access(): AccountAccessDao
    abstract fun account(): AccountDao
    abstract fun media(): MediaDao
}