package com.example.gilgoldzweig.movies.fragments.trailers

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.example.gilgoldzweig.movies.base.BasePresenter
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModel
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModelFactory

class TrailersFragmentPresenter(private val fragment: TrailersFragment) :
        BasePresenter<TrailersFragmentPresenterCallback> {

    override var callback: TrailersFragmentPresenterCallback = fragment

    private val selectedMovieViewModel = ViewModelProviders.of(
            fragment,
            SelectedMovieViewModelFactory)[SelectedMovieViewModel::class.java]

    override fun observe() {
        super.observe()
        selectedMovieViewModel.selectedMovieDetails.observe(fragment, Observer {
            if (it == null) return@Observer
            val videos = it.videos
            if (videos == null || videos.results.isEmpty()) {
                callback.onTrailersNotFound()
            } else {
                callback.onTrailersFound(videos.results)
            }
        })
    }
}
