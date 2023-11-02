package com.mmag.triviatraining.data.db


import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDataBaseModule {
    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext context: Context): TriviaDatabase {
        return Room.databaseBuilder(context, TriviaDatabase::class.java, "triviatraining.db").build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(database: TriviaDatabase): TriviaDao {
        return database.triviaDao()
    }

}
