package com.example.gilgoldzweig.movies.modules.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.models.Video
import com.example.gilgoldzweig.movies.modules.db.convertors.IntegerListTypeConverter
import com.example.gilgoldzweig.movies.modules.db.dao.MoviesDao

@Database(entities = [Movie::class], version = 1)
@TypeConverters(IntegerListTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}