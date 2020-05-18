package com.example.moviedb.data.remote

import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.remote.response.GetCastAndCrewResponse
import com.example.moviedb.data.remote.response.GetMovieListResponse
import com.example.moviedb.data.remote.response.GetMovieVideos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {

    @GET("3/movie/now_playing")
    suspend fun getLatestMovieAsync(@QueryMap hashMap: HashMap<String, String> = HashMap()): GetMovieListResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieAsync(@QueryMap hashMap: HashMap<String, String> = HashMap()): Movie

    @GET("3/movie/{movie_id}/credits")
    suspend fun getMovieCreditsAsync(@Path("movie_id") movieId: String): GetCastAndCrewResponse

    @GET("3/movie/{movie_id}/similar")
    suspend fun getSimilarMovieAsync(@Path("movie_id") movieId: String): GetMovieListResponse

    @GET("3/movie/{movie_id}/videos")
    suspend fun getVideoAsync(@Path("movie_id") movieId: String): GetMovieVideos

}

object ApiParams {
    const val PAGE = "page"
}