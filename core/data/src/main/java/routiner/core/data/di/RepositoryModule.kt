package routiner.core.data.di

import routiner.core.data.repository.DefaultCategoryRepository
import routiner.core.data.repository.DefaultDateRepository
import routiner.core.data.repository.DefaultRepeatRoutineRepository
import routiner.core.data.repository.DefaultReviewRepository
import routiner.core.data.repository.DefaultRoutineRepository
import routiner.core.data.repository.DefaultSettingRepository
import routiner.core.domain.repository.CategoryRepository
import routiner.core.domain.repository.DateRepository
import routiner.core.domain.repository.RepeatRoutineRepository
import routiner.core.domain.repository.ReviewRepository
import routiner.core.domain.repository.RoutineRepository
import routiner.core.domain.repository.SettingRepository
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
    abstract fun provideCategoryRepository(
        defaultCategoryRepository: DefaultCategoryRepository
    ): CategoryRepository

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

}
