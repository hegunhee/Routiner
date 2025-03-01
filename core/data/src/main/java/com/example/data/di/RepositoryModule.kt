package com.example.data.di

import com.example.data.repository.DefaultDateRepository
import com.example.data.repository.DefaultRepeatRoutineRepository
import com.example.domain.repository.Repository
import com.example.data.repository.DefaultRepository
import com.example.data.repository.DefaultReviewRepository
import com.example.data.repository.DefaultRoutineRepository
import com.example.data.repository.DefaultSettingRepository
import com.example.domain.repository.DateRepository
import com.example.domain.repository.RepeatRoutineRepository
import com.example.domain.repository.ReviewRepository
import com.example.domain.repository.RoutineRepository
import com.example.domain.repository.SettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideDateRepository(
        defaultDateRepository: DefaultDateRepository
    ): DateRepository

    @Singleton
    @Binds
    abstract fun provideRepeatRoutineRepository(
        defaultRepeatRoutineRepository: DefaultRepeatRoutineRepository
    ): RepeatRoutineRepository

    @Singleton
    @Binds
    abstract fun provideReviewRepository(
        defaultReviewRepository: DefaultReviewRepository
    ): ReviewRepository

    @Singleton
    @Binds
    abstract fun provideDefaultRoutineRepository(
        defaultRoutineRepository: DefaultRoutineRepository
    ): RoutineRepository

    @Singleton
    @Binds
    abstract fun provideDefaultSettingRepository(
        defaultSettingRepository: DefaultSettingRepository
    ): SettingRepository

    @Singleton
    @Binds
    abstract fun provideDefaultRepository(
        defaultRepository: DefaultRepository
    ): Repository

}
