package com.mmag.data.di

import com.mmag.data.network.TriviaService
import com.mmag.data.network.repository.NetworkRepository
import com.mmag.data.network.repository.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkRepositoryModule {
    @Singleton
    @Provides
    fun providesNetworkRepository(service: TriviaService): NetworkRepository =
        NetworkRepositoryImpl(service)
}