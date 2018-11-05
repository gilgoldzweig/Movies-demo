package com.example.gilgoldzweig.movies.fragments.details

import com.example.gilgoldzweig.movies.base.BasePresenterCallBack

interface DetailsFragmentPresenterCallback : BasePresenterCallBack {

	fun onMovieDescriptionLoaded(description: String)
}