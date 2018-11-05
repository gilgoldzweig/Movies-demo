package com.example.gilgoldzweig.movies.fragments.movies.adapters

import android.support.v7.util.DiffUtil
import com.example.gilgoldzweig.movies.models.Movie

class MoviesDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
        areItemsTheSame(oldItem, newItem)

}