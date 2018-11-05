package com.example.gilgoldzweig.movies.fragments.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.example.gilgoldzweig.movies.base.BasePresenter
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModel
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModelFactory

class DetailsFragmentPresenter(private val fragment: DetailsFragment) :
	BasePresenter<DetailsFragmentPresenterCallback> {

	override var callback: DetailsFragmentPresenterCallback = fragment

	private val selectedMovieViewModel = ViewModelProviders.of(
		fragment,
		SelectedMovieViewModelFactory)[SelectedMovieViewModel::class.java]

	override fun observe() {
		super.observe()
		selectedMovieViewModel.selectedMovieDetails.observe(fragment, Observer {
			if (it == null) return@Observer
			callback.onMovieDescriptionLoaded("""
				${it.tagline}

				${it.overview}
			""".trimIndent())
		})
	}

}