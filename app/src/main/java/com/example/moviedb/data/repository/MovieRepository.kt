package com.example.moviedb.data.repository

import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.SearchedMovie
import com.example.moviedb.data.remote.response.GetCastAndCrewResponse
import com.example.moviedb.data.remote.response.GetMovieListResponse
import com.example.moviedb.data.remote.response.GetMovieVideos

interface MovieRepository {

    suspend fun getMovieList(
        hashMap: HashMap<String, String> = HashMap()
    ): GetMovieListResponse

    suspend fun getCastAndCrew(
        movieId: String
    ): GetCastAndCrewResponse

    suspend fun getSimilarMovie(
        movieId: String
    ): GetMovieListResponse

    suspend fun getVideo(
        movieId: String
    ): GetMovieVideos

    suspend fun insertDB(
        list: List<Movie>
    )

    suspend fun updateDB(
        movie: Movie
    )

    /**
     * local movie db functions
     */
    suspend fun getMovieListLocal(): List<Movie>?

    suspend fun getSearchedMovieListLocal(): List<SearchedMovie>?

    suspend fun getMovieLocal(id: String): Movie?

    suspend fun insertLocal(movie: Movie)

    suspend fun insertSearchedMovieLocal(searchedMovie: SearchedMovie)

    suspend fun insertLocal(list: List<Movie>)

    suspend fun updateLocal(movie: Movie)

    suspend fun deleteMovieLocal(movie: Movie)

    suspend fun deleteMovieLocal(id: String)

    suspend fun deleteAllLocal()

    suspend fun getMoviePageLocal(pageSize: Int, pageIndex: Int): List<Movie>?

    suspend fun searchMovieLocal(movieTitle: String): List<Movie>?

}