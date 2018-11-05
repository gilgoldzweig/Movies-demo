package com.example.gilgoldzweig.movies.fragments.actors

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.example.gilgoldzweig.movies.base.BasePresenter
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModel
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModelFactory

class ActorsFragmentPresenter(private val fragment: ActorsFragment) :
  BasePresenter<ActorsFragmentPresenterCallback> {

  override var callback: ActorsFragmentPresenterCallback = fragment

  private val selectedMovieViewModel = ViewModelProviders.of(
    fragment,
    SelectedMovieViewModelFactory)[SelectedMovieViewModel::class.java]

  override fun observe() {
    super.observe()
    selectedMovieViewModel.selectedMovieDetails.observe(fragment, Observer {
      if (it == null) return@Observer

      val actors = it.castResponse.cast

      if (actors.isNullOrEmpty()) {
        callback.onActorsNotFound()
      } else {
        callback.onActorsFound(actors)
      }

    })
  }
}
