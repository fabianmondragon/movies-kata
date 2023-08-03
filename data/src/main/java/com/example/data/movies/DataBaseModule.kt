package com.example.data.movies

import android.content.Context
import androidx.room.Room
import com.example.data.database.MovieDao
import com.example.data.database.MoviesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MoviesDataBase {
        return Room.databaseBuilder(
            context,
            MoviesDataBase::class.java,
            "movies.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(
        moviesDataBase: MoviesDataBase
    ): MovieDao = moviesDataBase.movieDao()


}

