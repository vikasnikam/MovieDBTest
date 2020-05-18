package com.example.moviedb.di

import android.content.Context
import androidx.room.Room
import com.example.moviedb.data.constant.Constants
import com.example.moviedb.data.local.db.AppDatabase
import com.example.moviedb.data.repository.MovieRepository
import com.example.moviedb.data.repository.impl.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    // database
    single { createAppDatabase(get()) }
    single { createMovieDao(get()) }

    // repository
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}

fun createAppDatabase(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME).build()

fun createMovieDao(appDatabase: AppDatabase) = appDatabase.movieDao()
