package com.example.data.di

import com.example.domain.repository.Repository
import com.example.data.repository.DefaultRepository
import com.example.data.repository.DefaultSettingRepository
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
    abstract fun provideDefaultSettingRepository(
        defaultSettingRepository: DefaultSettingRepository
    ) : SettingRepository

    @Singleton
    @Binds
    abstract fun provideDefaultRepository(
        defaultRepository: DefaultRepository
    ) : Repository

}
