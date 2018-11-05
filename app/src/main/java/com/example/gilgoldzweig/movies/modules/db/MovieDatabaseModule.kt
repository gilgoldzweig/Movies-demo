package com.example.gilgoldzweig.movies.modules.db

import android.arch.persistence.room.Room
import com.example.gilgoldzweig.movies.application.MoviesApplication
import com.example.gilgoldzweig.movies.modules.db.dao.MoviesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: MoviesApplication) {

  private val database by lazy {
    Room.databaseBuilder(application, MovieDatabase::class.java, "MOVIES_DB")
      .build()
  }

  @Provides
  @Singleton
  fun provideDatabase(): MovieDatabase = database

  @Provides
  @Singleton
  fun provideMoviesDao(): MoviesDao = database.moviesDao

}
