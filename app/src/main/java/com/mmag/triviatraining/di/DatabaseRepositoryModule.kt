package com.mmag.triviatraining.di

import com.mmag.triviatraining.data.db.TriviaDao
import com.mmag.triviatraining.data.storage.repository.DatabaseRepository
import com.mmag.triviatraining.data.storage.repository.DatabaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseRepositoryModule {

    @Singleton
    @Provides
    fun providesDatabaseRepositoryModule(dao: TriviaDao): DatabaseRepository =
        DatabaseRepositoryImpl(dao)
}