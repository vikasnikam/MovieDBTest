package com.example.moviedb.data.model

import android.os.Parcelable
import com.example.moviedb.BuildConfig
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Crew(
    val credit_id: String?,
    val department: String?,
    val gender: Int?,
    val id: String?,
    val job: String?,
    val name: String?,
    val profile_path: String?
): Parcelable{
    fun getFullProfilePath() =
        if (profile_path.isNullOrBlank()) null else BuildConfig.SMALL_IMAGE_URL + profile_path
}