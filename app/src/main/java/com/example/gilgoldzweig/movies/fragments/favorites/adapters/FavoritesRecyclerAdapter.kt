package com.example.gilgoldzweig.movies.fragments.favorites.adapters

import android.content.Context
import android.support.v4.util.Pair
import android.view.View
import com.example.gilgoldzweig.movies.R
import com.example.gilgoldzweig.movies.consts.Genre
import com.example.gilgoldzweig.movies.consts.IMAGE_LOW_RES_BASE_URL
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.ui.GlideApp
import com.example.gilgoldzweig.movies.ui.OnMovieClickedListener
import goldzweigapps.com.annotations.annotations.GencyclerDataType
import goldzweigapps.com.gencycler.recyclerAdapters.GeneratedFavoritesRecyclerAdapter

class FavoritesRecyclerAdapter(
    context: Context,
    var favorites: ArrayList<GencyclerDataType> = ArrayList(),
    var onMovieClickedListener: OnMovieClickedListener? = null
) :
    GeneratedFavoritesRecyclerAdapter(context, favorites) {


    private val glide = GlideApp.with(context)
    private val posterTransitionName = context.getString(R.string.source_poster_transition)
    private val titleTransitionName = context.getString(R.string.source_title_transition)


    override fun MovieViewHolder.onBindMovieViewHolder(position: Int, movie: Movie) {
        movieItemTitleText.text = movie.title

        glide.load("$IMAGE_LOW_RES_BASE_URL${movie.posterPath}")
            .centerCrop()
            .into(movieItemPosterImage)

        onClick {
            onMovieClickedListener?.onMovieClicked(
                movie,
                arrayOf(
                    Pair.create<View, String>(movieItemPosterImage, posterTransitionName),
                    Pair.create<View, String>(movieItemTitleText, titleTransitionName)
                )
            )
        }

        movieItemGenreText.text = Genre.generateGenresString(movie.genreIds)
    }

}