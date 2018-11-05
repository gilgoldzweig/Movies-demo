package com.example.gilgoldzweig.movies.activities.info

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.example.gilgoldzweig.movies.application.MoviesApplication
import com.example.gilgoldzweig.movies.base.BasePresenter
import com.example.gilgoldzweig.movies.extensions.Timber
import com.example.gilgoldzweig.movies.extensions.Timber.e
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.modules.db.dao.MoviesDao
import com.example.gilgoldzweig.movies.modules.db.dao.delete
import com.example.gilgoldzweig.movies.modules.db.dao.insert
import com.example.gilgoldzweig.movies.modules.network.retrofit.MoviesRetrofitService
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModel
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModelFactory
import com.example.gilgoldzweig.movies.ui.GlideApp
import goldzweigapps.com.reactive.runSafeOnIO
import goldzweigapps.com.reactive.runSafeOnMain
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieInfoActivityPresenter(private val activity: MovieInfoActivity) :
        BasePresenter<MovieInfoActivityPresenterCallback> {

    @Inject
    lateinit var moviesDao: MoviesDao

    override var callback: MovieInfoActivityPresenterCallback = activity

    private val compositeDisposable = CompositeDisposable()

    private val selectedMovieViewModel = ViewModelProviders.of(
            activity,
            SelectedMovieViewModelFactory)[SelectedMovieViewModel::class.java]

    private var selectedMovie: Movie? = null

    init {
        MoviesApplication.movieInformationComponent.inject(this)

        selectedMovieViewModel.selectedMovie.observe(activity, Observer { movie ->
            if (movie == null) return@Observer
            selectedMovie = movie
            callback.onMovieUpdated(movie)
            getFavoriteState(movie.id)
        })
    }

    private fun getFavoriteState(movieId: Int) {
        compositeDisposable.add(moviesDao.find(movieId)
                .runSafeOnMain()
                .subscribe({
                    callback.onMovieFavoriteStateFound(true)
                }, {
                    callback.onMovieFavoriteStateFound(false)
                }))
    }

    fun addToFavorites() {
        selectedMovie?.let {
            compositeDisposable.add(
                    moviesDao.insert(it)
                            .runSafeOnIO()
                            .subscribe({
                                Timber.d("Movie added to favorites")
                            }, ::e))
        }
    }

    fun removeFromFavorites() {
        selectedMovie?.let {
            compositeDisposable.add(
                    moviesDao.delete(it)
                            .runSafeOnIO()
                            .subscribe({
                                Timber.d("Movie added to favorites")
                            }, ::e))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}