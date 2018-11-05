package com.example.gilgoldzweig.movies.ui

import android.support.v4.util.Pair
import android.view.View
import com.example.gilgoldzweig.movies.models.Movie

interface OnMovieClickedListener {
    fun onMovieClicked(selectedMovie: Movie, sharedElements: Array<Pair<View, String>>)
}