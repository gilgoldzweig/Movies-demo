package com.example.gilgoldzweig.movies.modules.di

import com.example.gilgoldzweig.movies.activities.info.MovieInfoActivityPresenter
import com.example.gilgoldzweig.movies.activities.info.MovieInfoActivityPresenter_MembersInjector
import com.example.gilgoldzweig.movies.modules.db.DatabaseModule
import com.example.gilgoldzweig.movies.modules.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, DatabaseModule::class])
@Singleton
interface MovieInformationComponent {

  fun inject(movieInfoActivityPresenter: MovieInfoActivityPresenter)

}
