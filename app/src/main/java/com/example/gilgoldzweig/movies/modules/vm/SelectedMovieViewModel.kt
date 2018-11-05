package com.example.gilgoldzweig.movies.modules.vm

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.gilgoldzweig.movies.application.MoviesApplication
import com.example.gilgoldzweig.movies.extensions.Timber
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.models.MovieDetails
import com.example.gilgoldzweig.movies.modules.network.retrofit.MoviesRetrofitService
import goldzweigapps.com.reactive.runSafeOnIO
import goldzweigapps.com.reactive.runSafeOnMain
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class SelectedMovieViewModel : ViewModel() {


    @Inject
    lateinit var moviesRetrofitService: MoviesRetrofitService

    private val compositeDisposable = CompositeDisposable()

    var selectedMovie = MutableLiveData<Movie>()
    var selectedMovieDetails = MutableLiveData<MovieDetails>()

    init {
        MoviesApplication.networkComponent.inject(this)
    }

    fun setCurrentSelectedMovie(movie: Movie) {
        selectedMovie.postValue(movie)
        fetchMovieDetails(movie.id)
    }

    private fun fetchMovieDetails(movieId: Int) {
        compositeDisposable.add(moviesRetrofitService.getMovieDetails(movieId)
                .runSafeOnMain()
                .subscribe({
                    selectedMovieDetails.postValue(it)
                }, {
                    Timber.e(it)
                    selectedMovieDetails.postValue(null)
                }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
