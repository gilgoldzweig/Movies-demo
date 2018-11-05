package com.example.gilgoldzweig.movies.fragments.trailers

import com.example.gilgoldzweig.movies.base.BasePresenterCallBack
import com.example.gilgoldzweig.movies.models.Video

interface TrailersFragmentPresenterCallback : BasePresenterCallBack {

    fun onTrailersFound(videos: List<Video>)
    fun onTrailersNotFound()
}
