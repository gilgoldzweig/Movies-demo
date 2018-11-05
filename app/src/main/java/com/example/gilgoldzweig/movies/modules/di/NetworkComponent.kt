package com.example.gilgoldzweig.movies.modules.di

import com.example.gilgoldzweig.movies.activities.info.MovieInfoActivityPresenter
import com.example.gilgoldzweig.movies.modules.network.NetworkModule
import com.example.gilgoldzweig.movies.modules.network.paging.MoviesDataSource
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface NetworkComponent {
    fun inject(moviesDataSource: MoviesDataSource)
    fun inject(selectedMovieViewModel: SelectedMovieViewModel)
}