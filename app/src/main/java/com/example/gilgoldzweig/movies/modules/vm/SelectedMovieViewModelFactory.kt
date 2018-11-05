package com.example.gilgoldzweig.movies.modules.vm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * Creating a custom factory in order to preserve state over multiple activities
 * **/
object SelectedMovieViewModelFactory : ViewModelProvider.Factory {

  private val model by lazy {  SelectedMovieViewModel() }

  override fun <T : ViewModel?> create(modelClass: Class<T>) = model as T
}
