package com.example.data.di

import com.example.data.db.SharedPreferenceManager
import com.example.data.db.dao.*
import com.example.domain.repository.Repository
import com.example.data.repository.DefaultRepository
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
        categoryDao: CategoryDao,
        sharedPreferenceManager: SharedPreferenceManager
    ) : Repository {
        return DefaultRepository(routineDao,dateDao,reviewDao,repeatRoutineDao,categoryDao,sharedPreferenceManager)
    }
}