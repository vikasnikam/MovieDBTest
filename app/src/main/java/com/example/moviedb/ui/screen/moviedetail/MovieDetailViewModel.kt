package com.example.moviedb.ui.screen.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.model.Cast
import com.example.moviedb.data.model.Crew
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.repository.MovieRepository
import com.example.moviedb.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    val movie = MutableLiveData<Movie>()
    val cast = MutableLiveData<ArrayList<Cast>>()
    val crew = MutableLiveData<ArrayList<Crew>>()
    val similarMovie = MutableLiveData<ArrayList<Movie>>()
    private val favoriteChanged = MutableLiveData<Boolean>().apply { value = false }

    /**
     * load cast and crew
     */
    fun loadCastAndCrew(movieId: String) {
        if (cast.value != null) return
        viewModelScope.launch {
            try {
                cast.value = movieRepository.getCastAndCrew(movieId).cast
                crew.value = movieRepository.getCastAndCrew(movieId).crew
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    /**
     * load similar movie
     */
    fun loadSimilarMovie(movieId: String) {
        if (similarMovie.value != null) return
        viewModelScope.launch {
            try {
                similarMovie.value = movieRepository.getSimilarMovie(movieId).results
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}