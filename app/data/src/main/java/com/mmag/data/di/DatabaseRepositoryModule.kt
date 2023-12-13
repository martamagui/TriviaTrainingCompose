package com.mmag.data.di

import com.mmag.data.db.TriviaDao
import com.mmag.data.db.repository.DatabaseRepository
import com.mmag.data.db.repository.DatabaseRepositoryImpl
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