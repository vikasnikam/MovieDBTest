package com.example.moviedb.ui.screen.latestmovielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.SearchedMovie
import com.example.moviedb.data.remote.ApiParams
import com.example.moviedb.data.repository.MovieRepository
import com.example.moviedb.ui.base.BaseLoadMoreRefreshViewModel
import kotlinx.coroutines.launch


class LatestMovieViewModel(
     val movieRepository: MovieRepository
) : BaseLoadMoreRefreshViewModel<Movie>() {

    val isRecentVisible = MutableLiveData<Boolean>().apply { value = true }
    val isSearchVisible = MutableLiveData<Boolean>().apply { value = true }
    val searchText = MutableLiveData<String>().apply { value = "" }
    val movie = MutableLiveData<List<Movie>>()
    val selectedMovie = MutableLiveData<List<SearchedMovie>>()


    override fun loadData(page: Int) {
        val hashMap = HashMap<String, String>()
        hashMap[ApiParams.PAGE] = page.toString()

        viewModelScope.launch {
            try {
                movieRepository.getMovieList(hashMap).results?.let { movieRepository.insertDB(it) }
                onLoadSuccess(page, movieRepository.getMovieListLocal())
                movie.value=movieRepository.getMovieListLocal()
                selectedMovie.value=movieRepository.getSearchedMovieListLocal()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }




}