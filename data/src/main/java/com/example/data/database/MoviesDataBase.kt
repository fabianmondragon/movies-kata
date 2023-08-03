package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.converters.Converters
import com.example.data.database.entities.MovieEntity

@Database (entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class) // Add this line
abstract class MoviesDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}