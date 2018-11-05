package com.example.gilgoldzweig.movies.activities.info

import com.example.gilgoldzweig.movies.base.BasePresenterCallBack
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.models.MovieDetails
import com.example.gilgoldzweig.movies.models.Video

interface MovieInfoActivityPresenterCallback : BasePresenterCallBack {
    fun onMovieUpdated(movie: Movie)
    fun onMovieFavoriteStateFound(favorite: Boolean)
}