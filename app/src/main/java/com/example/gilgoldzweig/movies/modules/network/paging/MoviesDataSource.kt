package com.example.gilgoldzweig.movies.modules.network.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.example.gilgoldzweig.movies.application.MoviesApplication
import com.example.gilgoldzweig.movies.extensions.Timber
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.modules.network.retrofit.MoviesRetrofitService
import com.example.gilgoldzweig.movies.modules.network.state.State
import goldzweigapps.com.reactive.runSafeOnMain
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import javax.inject.Inject

class MoviesDataSource(private val compositeDisposable: CompositeDisposable) :
	PageKeyedDataSource<Int, Movie>() {

	@Inject
	lateinit var moviesRetrofitService: MoviesRetrofitService

	var stateLiveData: MutableLiveData<State> = MutableLiveData()
		private set

	private var retryCompletable: Completable? = null

	init {
		MoviesApplication.networkComponent.inject(this)
	}


	override fun loadInitial(
		params: LoadInitialParams<Int>,
		callback: LoadInitialCallback<Int, Movie>) {
		updateState(State.LOADING)
		compositeDisposable.add(
			moviesRetrofitService.getMovies()
				.subscribe({ response ->
					MoviesDataSourceFactory.reloading = false
					updateState(State.DONE)
					callback.onResult(
						response.movies, 1, response.totalPages,
						null, 2)
				}, {
					Timber.e(it)
					if (!MoviesDataSourceFactory.reloading) {
						updateState(State.ERROR)
						setRetry(Action { loadInitial(params, callback) })
					} else {
						updateState(State.RELOADING_ERROR)
					}
				})
		)
	}

	override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
		updateState(State.LOADING)
		compositeDisposable.add(
			moviesRetrofitService.getMovies(params.key)
				.subscribe({ response ->
					updateState(State.DONE)
					callback.onResult(response.movies, params.key + 1)
				}, {
					Timber.e(it)
					updateState(State.ERROR)
					setRetry(Action { loadAfter(params, callback) })
				})
		)

	}

	override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) = Unit


	private fun updateState(state: State) = stateLiveData.postValue(state)

	fun retry() {
		if (retryCompletable != null) {
			compositeDisposable.add(
				retryCompletable!!
					.runSafeOnMain()
					.subscribe())
		}
	}

	private fun setRetry(action: Action?) {
		retryCompletable = if (action == null) null else Completable.fromAction(action)
	}

	fun reload() {
		MoviesDataSourceFactory.reloading = true
		invalidate()
	}
}