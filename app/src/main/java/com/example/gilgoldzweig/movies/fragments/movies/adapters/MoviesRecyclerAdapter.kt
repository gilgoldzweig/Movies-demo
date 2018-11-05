package com.example.gilgoldzweig.movies.fragments.movies.adapters

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v4.util.Pair
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gilgoldzweig.movies.R
import com.example.gilgoldzweig.movies.consts.Genre
import com.example.gilgoldzweig.movies.consts.IMAGE_LOW_RES_BASE_URL
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.modules.network.state.State
import com.example.gilgoldzweig.movies.ui.GlideApp
import com.example.gilgoldzweig.movies.ui.OnMovieClickedListener
import java.lang.UnsupportedOperationException

class MoviesRecyclerAdapter(
    private val context: Context,
    private var onMovieClickedListener: OnMovieClickedListener? = null
) :
    PagedListAdapter<Movie, RecyclerView.ViewHolder>(MoviesDiffCallback()) {


    private val inflater = LayoutInflater.from(context)
    private val glide = GlideApp.with(context)
    private val posterTransitionName = context.getString(R.string.source_poster_transition)
    private val titleTransitionName = context.getString(R.string.source_title_transition)

    var currentState: State = State.LOADING
        set(value) {
            field = value
            notifyItemChanged(super.getItemCount())
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_movie ->
                MovieItemViewHolder(inflater.inflate(viewType, parent, false))

            R.layout.item_movie_footer ->
                MovieItemFooterViewHolder(inflater.inflate(viewType, parent, false))

            else -> throw UnsupportedOperationException("view type provided is not supported")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieItemViewHolder -> {
                val movie = getItem(position) ?: return

                holder.movieTitleTextView.text = movie.title

                glide.load("$IMAGE_LOW_RES_BASE_URL${movie.posterPath}")
                    .centerCrop()
                    .into(holder.moviePosterImageView)

                holder.itemView.setOnClickListener {
                    onMovieClickedListener?.onMovieClicked(
                        movie,
                        arrayOf(
                            Pair.create<View, String>(holder.moviePosterImageView, posterTransitionName),
                            Pair.create<View, String>(holder.movieTitleTextView, titleTransitionName)
                        )
                    )
                }

                holder.movieGenreTextView.text = Genre.generateGenresString(movie.genreIds)
            }
        }
    }

    fun isFooter(position: Int) = hasFooter() && position > super.getItemCount()

    override fun getItemViewType(position: Int) =
        if (position < super.getItemCount()) R.layout.item_movie else R.layout.item_movie_footer

    override fun getItemCount(): Int =
        super.getItemCount() + if (hasFooter()) 1 else 0


    private fun hasFooter() =
        super.getItemCount() != 0 && (currentState == State.LOADING || currentState == State.ERROR)

}

internal class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val moviePosterImageView: AppCompatImageView = view.findViewById(R.id.movie_item_poster_image)

    val movieTitleTextView: AppCompatTextView = view.findViewById(R.id.movie_item_title_text)

    val movieGenreTextView: AppCompatTextView = view.findViewById(R.id.movie_item_genre_text)

}

internal class MovieItemFooterViewHolder(view: View) : RecyclerView.ViewHolder(view)