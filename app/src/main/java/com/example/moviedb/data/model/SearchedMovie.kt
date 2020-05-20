package com.example.moviedb.data.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviedb.BuildConfig
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "SearchedMovie")
data class SearchedMovie(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val adult: Boolean? = false,
    val backdrop_path: String? = null,
    val budget: Int? = null,
    val homepage: String? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = false,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    var isFavorite: Boolean? = false
) : Parcelable {

    fun getFullBackdropPath() =
        if (backdrop_path.isNullOrBlank()) null else BuildConfig.SMALL_IMAGE_URL + backdrop_path

    fun getFullPosterPath() =
        if (poster_path.isNullOrBlank()) null else BuildConfig.SMALL_IMAGE_URL + poster_path

    fun toMovie() = Movie(
        id=id,
        adult=adult,
        backdrop_path=backdrop_path,
        budget=budget,
        homepage=homepage,
        imdb_id=imdb_id,
        original_language=original_language,
        original_title=original_title,
        overview=overview,
        popularity=popularity,
        poster_path=poster_path,
        release_date=release_date,
        revenue=revenue,
        runtime=runtime,
        status=status,
        tagline=tagline,
        title=title,
        video=video,
        vote_average=vote_average,
        vote_count=vote_count,
        isFavorite=isFavorite
    )
}