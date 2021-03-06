package com.example.moviedb.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedb.data.local.dao.MovieDao
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.SearchedMovie

@Database(entities = [Movie::class,SearchedMovie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}