package com.example.moviedb.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.SearchedMovie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getMovieList(): List<Movie>?

    @Query("SELECT * FROM movie WHERE movie.id = :id")
    suspend fun getMovie(id: String): Movie?

    @Insert(onConflict = IGNORE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = IGNORE)
    suspend fun insertSearchedMovie(searchedMovie: SearchedMovie)

    @Query("SELECT * FROM SearchedMovie")
    suspend fun getSearchedMovieList(): List<SearchedMovie>?

    @Insert(onConflict = IGNORE)
    suspend fun insert(list: List<Movie>)

    @Insert(onConflict = REPLACE)
    suspend fun update(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteMovie(id: String)

    @Query("DELETE FROM movie")
    suspend fun deleteAll()


    @Query("SELECT * FROM movie LIMIT :pageSize OFFSET :pageIndex")
    suspend fun getMoviePage(pageSize: Int, pageIndex: Int): List<Movie>?

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :search || '%'")
    suspend fun searchMovie(search: String?): List<Movie>?

}