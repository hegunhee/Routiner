package com.hegunhee.routiner.di

import android.content.Context
import androidx.room.Room
import com.hegunhee.routiner.db.*
import com.hegunhee.routiner.db.dao.DateDao
import com.hegunhee.routiner.db.dao.RepeatRoutineDao
import com.hegunhee.routiner.db.dao.ReviewDao
import com.hegunhee.routiner.db.dao.RoutineDao
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
    fun provideRoutineDao(database : RoutinerDatabase) : RoutineDao {
        return database.routineDao()
    }

    @Singleton
    @Provides
    fun provideDateDao(database : RoutinerDatabase) : DateDao {
        return database.dateDao()
    }

    @Singleton
    @Provides
    fun provideReviewDao(database: RoutinerDatabase) : ReviewDao {
        return database.reviewDao()
    }

    @Singleton
    @Provides
    fun provideRepeatRoutineDao(database: RoutinerDatabase) : RepeatRoutineDao {
        return database.repeatRoutineDao()
    }
}
