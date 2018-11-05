package com.example.gilgoldzweig.movies.fragments.movies

import android.arch.paging.PagedList
import com.example.gilgoldzweig.movies.base.BasePresenterCallBack
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.modules.network.state.State

interface MoviesFragmentPresenterCallback : BasePresenterCallBack {

    fun onMoviesInitiating()
    fun onMoviesFailedToLoad()
    fun onMoviesUpdated(movies: PagedList<Movie>)
    fun onStateUpdated(state: State)
}