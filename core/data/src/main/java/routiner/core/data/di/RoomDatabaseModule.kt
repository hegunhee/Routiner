package routiner.core.data.di

import android.content.Context
import androidx.room.Room
import routiner.core.data.db.RoutinerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import routiner.core.data.db.dao.CategoryDao
import routiner.core.data.db.dao.DateDao
import routiner.core.data.db.dao.RepeatRoutineDao
import routiner.core.data.db.dao.ReviewDao
import routiner.core.data.db.dao.RoutineDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideRoutinerDatabase(@ApplicationContext context : Context) : RoutinerDatabase {
        return Room.databaseBuilder(context, RoutinerDatabase::class.java, RoutinerDatabase.APP_NAME).build()
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

    @Singleton
    @Provides
    fun provideCategoryDao(database: RoutinerDatabase) : CategoryDao {
        return database.categoryDao()
    }
}
