package com.example.moviedb.data.repository.impl

import com.example.moviedb.data.local.dao.MovieDao
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.remote.ApiService
import com.example.moviedb.data.remote.response.GetCastAndCrewResponse
import com.example.moviedb.data.remote.response.GetMovieListResponse
import com.example.moviedb.data.remote.response.GetMovieVideos
import com.example.moviedb.data.repository.MovieRepository

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getMovieList(
        hashMap: HashMap<String, String>
    ): GetMovieListResponse {
        return apiService.getLatestMovieAsync(hashMap)
    }

    override suspend fun getCastAndCrew(movieId: String): GetCastAndCrewResponse {
        return apiService.getMovieCreditsAsync(movieId)
    }

    override suspend fun getSimilarMovie(movieId: String): GetMovieListResponse {
        return apiService.getSimilarMovieAsync(movieId)
    }
    override suspend fun getVideo(movieId: String): GetMovieVideos {
        return apiService.getVideoAsync(movieId)
    }

    override suspend fun insertDB(
        list: List<Movie>
    ) {
        movieDao.insert(list)
    }

    override suspend fun updateDB(
        movie: Movie
    ) {
        movieDao.update(movie)
    }

    override suspend fun getMovieListLocal(): List<Movie>? {
        return movieDao.getMovieList()
    }

    override suspend fun getMovieLocal(id: String): Movie? {
        return movieDao.getMovie(id)
    }

    override suspend fun insertLocal(movie: Movie) {
        return movieDao.insert(movie)
    }

    override suspend fun insertLocal(list: List<Movie>) {
        return movieDao.insert(list)
    }

    override suspend fun updateLocal(movie: Movie) {
        return movieDao.update(movie)
    }

    override suspend fun deleteMovieLocal(movie: Movie) {
        return movieDao.deleteMovie(movie)
    }

    override suspend fun deleteMovieLocal(id: String) {
        return movieDao.deleteMovie(id)
    }

    override suspend fun deleteAllLocal() {
        return movieDao.deleteAll()
    }

    override suspend fun getMoviePageLocal(pageSize: Int, pageIndex: Int): List<Movie>? {
        return movieDao.getMoviePage(pageSize, pageIndex)
    }

    override suspend fun searchMovieLocal(movieTitle: String): List<Movie>? {
         return  movieDao.searchMovie(movieTitle)
    }

}