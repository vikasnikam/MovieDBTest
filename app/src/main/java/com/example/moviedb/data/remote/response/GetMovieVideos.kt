package com.example.moviedb.data.remote.response

import com.example.moviedb.data.model.Video

class GetMovieVideos (
    val videos: List<Video>?=null
): BaseResponse()