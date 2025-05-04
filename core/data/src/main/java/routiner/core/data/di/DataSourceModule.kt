package routiner.core.data.di

import routiner.core.data.datasource.local.DefaultLocalDataSource
import routiner.core.data.datasource.local.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(
        defaultLocalDataSource : DefaultLocalDataSource
    ) : LocalDataSource
}