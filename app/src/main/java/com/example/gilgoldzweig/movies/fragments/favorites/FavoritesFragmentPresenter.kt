package com.example.gilgoldzweig.movies.fragments.favorites

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.example.gilgoldzweig.movies.application.MoviesApplication
import com.example.gilgoldzweig.movies.base.BasePresenter
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.modules.db.dao.MoviesDao
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModel
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModelFactory
import javax.inject.Inject

class FavoritesFragmentPresenter(private val fragment: FavoritesFragment) : BasePresenter<FavoritesFragmentPresenterCallback> {

    override var callback: FavoritesFragmentPresenterCallback = fragment

    @Inject
    lateinit var moviesDao: MoviesDao

    private val selectedMovieViewModel =
            ViewModelProviders.of(fragment,
                    SelectedMovieViewModelFactory)[SelectedMovieViewModel::class.java]

    init {
        MoviesApplication.databaseComponent.inject(this)
    }

    override fun observe() {
        moviesDao.getAllFavorites()
                .observe(fragment, Observer {
                    if (it == null) return@Observer
                    callback.onFavoritesUpdated(it)
                })
    }

    fun selectMovie(movie: Movie) {
        selectedMovieViewModel.setCurrentSelectedMovie(movie)
    }
}
