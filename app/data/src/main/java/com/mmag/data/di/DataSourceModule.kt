package com.mmag.data.di

import com.mmag.data.data_source.DataSourceRepository
import com.mmag.data.data_source.DataSourceRepositoryImpl
import com.mmag.data.db.repository.DatabaseRepository
import com.mmag.data.network.repository.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesDataSourceRepository(
        networkRepository: NetworkRepository,
        dataBaseRepository: DatabaseRepository
    ): DataSourceRepository = DataSourceRepositoryImpl(networkRepository, dataBaseRepository)


}