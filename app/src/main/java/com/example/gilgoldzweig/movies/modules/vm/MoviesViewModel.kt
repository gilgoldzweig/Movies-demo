package com.example.gilgoldzweig.movies.modules.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.modules.network.paging.MoviesDataSource
import com.example.gilgoldzweig.movies.modules.network.paging.MoviesDataSourceFactory
import com.example.gilgoldzweig.movies.modules.network.state.State
import io.reactivex.disposables.CompositeDisposable

class MoviesViewModel : ViewModel() {


    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 20
    private val moviesDataSourceFactory = MoviesDataSourceFactory(compositeDisposable)
    var moviesList: LiveData<PagedList<Movie>>

    val state: LiveData<State>
        get() = Transformations.switchMap<MoviesDataSource,
                State>(moviesDataSourceFactory.moviesDataSourceLiveData, MoviesDataSource::stateLiveData)


    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()

        moviesList = LivePagedListBuilder<Int, Movie>(moviesDataSourceFactory, config).build()
    }


    fun retry() {
        moviesDataSourceFactory.moviesDataSourceLiveData.value?.retry()
    }

    fun reload() {
        moviesDataSourceFactory.moviesDataSourceLiveData.value?.reload()
    }

    fun isListEmpty() =
        moviesList.value.isNullOrEmpty()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}