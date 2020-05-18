package com.example.moviedb.di

import com.example.moviedb.ui.screen.main.MainActivityViewModel
import com.example.moviedb.ui.screen.latestmovielist.LatestMovieViewModel
import com.example.moviedb.ui.screen.moviedetail.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityViewModel() }
    viewModel { LatestMovieViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}