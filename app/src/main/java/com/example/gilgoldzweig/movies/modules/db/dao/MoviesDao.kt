package com.example.gilgoldzweig.movies.modules.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.gilgoldzweig.movies.models.Movie
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MoviesDao {

    @Insert
    fun add(vararg movies: Movie)

    @Delete
    fun remove(vararg movies: Movie)

    @Query("SELECT * FROM movies")
    fun getAllFavorites(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun find(id: Int): Single<Movie?>

}
/**
 * Supportive functions to make it safe to call add
 * */
fun MoviesDao.insert(vararg movies: Movie) = Completable.create { emitter ->
    add(*movies)
    emitter.onComplete()
}

/**
 * Supportive functions to make it safe to call remove
 * */
fun MoviesDao.delete(vararg movies: Movie) = Completable.create { emitter ->
    remove(*movies)
    emitter.onComplete()
}