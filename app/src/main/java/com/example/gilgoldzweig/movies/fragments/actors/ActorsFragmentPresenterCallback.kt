package com.example.gilgoldzweig.movies.fragments.actors

import com.example.gilgoldzweig.movies.base.BasePresenterCallBack
import com.example.gilgoldzweig.movies.models.Cast

interface ActorsFragmentPresenterCallback : BasePresenterCallBack {

  fun onActorsFound(videos: List<Cast>)
  fun onActorsNotFound()
}
