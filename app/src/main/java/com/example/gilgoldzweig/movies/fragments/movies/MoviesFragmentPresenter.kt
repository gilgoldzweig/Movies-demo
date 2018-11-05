package com.example.gilgoldzweig.movies.fragments.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.example.gilgoldzweig.movies.base.BasePresenter
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.modules.vm.MoviesViewModel
import com.example.gilgoldzweig.movies.modules.network.state.State
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModel
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModelFactory

class MoviesFragmentPresenter(private val fragment: MoviesFragment) : BasePresenter<MoviesFragmentPresenterCallback> {

    override var callback: MoviesFragmentPresenterCallback = fragment
    private val moviesViewModel = ViewModelProviders.of(fragment.activity!!)[MoviesViewModel::class.java]
    private val selectedMovieViewModel = ViewModelProviders.of(fragment, SelectedMovieViewModelFactory)[SelectedMovieViewModel::class.java]

    override fun observe() {

        moviesViewModel.moviesList.observe(fragment, Observer {
            if (it == null) return@Observer
            callback.onMoviesUpdated(it)
        })

        moviesViewModel.state.observe(fragment, Observer {
            state ->

            if (moviesViewModel.isListEmpty()) {
                if (state == State.LOADING) callback.onMoviesInitiating()

                if (state == State.ERROR) callback.onMoviesFailedToLoad()
            } else {
                callback.onStateUpdated(state ?: State.DONE)
            }
        })
    }

    fun selectMovie(movie: Movie) {
        selectedMovieViewModel.setCurrentSelectedMovie(movie)
    }

    fun retryLastAction() = moviesViewModel.retry()
    fun reload() = moviesViewModel.reload()
}
