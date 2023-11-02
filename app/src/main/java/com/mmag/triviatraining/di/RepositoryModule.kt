package com.mmag.triviatraining.di

import com.mmag.triviatraining.data.network.NetworkAPI
import com.mmag.triviatraining.data.network.TriviaService
import com.mmag.triviatraining.data.network.repository.NetworkRepository
import com.mmag.triviatraining.data.network.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesNetworkRepository(service: TriviaService): NetworkRepository{
        return NetworkRepositoryImpl(service)
    }

}