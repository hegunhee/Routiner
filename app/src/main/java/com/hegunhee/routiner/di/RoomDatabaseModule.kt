package com.hegunhee.routiner.di

import android.content.Context
import androidx.room.Room
import com.hegunhee.routiner.db.RoutineDao
import com.hegunhee.routiner.db.RoutinerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideRoutinerDatabase(@ApplicationContext context : Context) : RoutinerDatabase{
        return Room.databaseBuilder(context,RoutinerDatabase::class.java,RoutinerDatabase.APP_NAME).build()
    }

    @Singleton
    @Provides
    fun provideRoutineDao(database : RoutinerDatabase) : RoutineDao{
        return database.routineDao()
    }
}
