package com.example.gilgoldzweig.movies.fragments.favorites

import com.example.gilgoldzweig.movies.base.BasePresenterCallBack
import com.example.gilgoldzweig.movies.models.Movie

interface FavoritesFragmentPresenterCallback : BasePresenterCallBack {
    fun onFavoritesFailedToLoad()
    fun onFavoritesUpdated(favorites: List<Movie>)
}