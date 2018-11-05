package com.example.gilgoldzweig.movies.modules.network.retrofit

import com.example.gilgoldzweig.movies.BuildConfig
import com.example.gilgoldzweig.movies.models.MovieDetails
import com.example.gilgoldzweig.movies.models.MoviesResponse
import com.example.gilgoldzweig.movies.models.VideoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.Appendable

interface MoviesRetrofitService {

    @GET("discover/movie")
    fun getMovies(
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("api_key") api: String = BuildConfig.TMDB_API_KEY): Single<MoviesResponse>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") movieId: String,
        @Query("api_key") api: String = BuildConfig.TMDB_API_KEY): Single<VideoResponse>


    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendableFields: String = "videos,casts",
        @Query("api_key") api: String = BuildConfig.TMDB_API_KEY): Single<MovieDetails>
}