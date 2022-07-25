package com.hegunhee.routiner.di

import com.hegunhee.routiner.db.dao.*
import com.hegunhee.routiner.model.DefaultRepository
import com.hegunhee.routiner.model.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDefaultRepository(
        routineDao : RoutineDao,
        dateDao : DateDao,
        reviewDao: ReviewDao,
        repeatRoutineDao: RepeatRoutineDao,
        categoryDao: CategoryDao
    ) : Repository {
        return DefaultRepository(routineDao,dateDao,reviewDao,repeatRoutineDao,categoryDao)
    }
}