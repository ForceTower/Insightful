package dev.forcetower.instascan.dagger.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.forcetower.instascan.core.source.local.InstrackDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): InstrackDB {
        return Room.databaseBuilder(context, InstrackDB::class.java, "tracker.db").build()
    }
}