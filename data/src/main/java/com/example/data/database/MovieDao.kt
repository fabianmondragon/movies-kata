package com.example.data.database

import androidx.room.*
import com.example.data.database.entities.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( movies: MovieEntity)

}