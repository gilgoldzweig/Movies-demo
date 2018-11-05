package com.example.gilgoldzweig.movies.modules.network.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.gilgoldzweig.movies.models.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviesDataSourceFactory(private val compositeDisposable: CompositeDisposable) :
    DataSource.Factory<Int, Movie>() {

    val moviesDataSourceLiveData = MutableLiveData<MoviesDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val newsDataSource = MoviesDataSource(compositeDisposable)
        moviesDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }

    companion object {
        internal var reloading = false
    }
}