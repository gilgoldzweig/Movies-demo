package com.example.gilgoldzweig.movies.modules.di

import com.example.gilgoldzweig.movies.fragments.favorites.FavoritesFragmentPresenter
import com.example.gilgoldzweig.movies.modules.db.DatabaseModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class])
@Singleton
interface DatabaseComponent {
  fun inject(favoritesFragmentPresenter: FavoritesFragmentPresenter)
}
