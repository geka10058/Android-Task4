package com.example.android_task4.data

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideFilmDatabase(application: Application): BookDatabase {
        return BookDatabase.getInstance(application)
    }

    @Singleton
    @Provides
    fun provideFilmDao(database: BookDatabase): BookDao {
        return database.getBooksDao()
    }
}