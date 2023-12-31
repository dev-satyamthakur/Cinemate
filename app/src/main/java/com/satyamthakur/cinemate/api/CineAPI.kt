package com.satyamthakur.cinemate.api

import com.satyamthakur.cinemate.BuildConfig
import com.satyamthakur.cinemate.models.MovieCastResponse
import com.satyamthakur.cinemate.models.MovieDetailsResponse
import com.satyamthakur.cinemate.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CineAPI {

    @GET("movie/{what}")
    suspend fun getPopularMovies(
        @Header("Authorization") token: String = "Bearer ${BuildConfig.TMDB_TOKEN}",
        @Path("what") what: String,
        @Query("region") region: String = "",
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Header("Authorization") token: String = "Bearer ${BuildConfig.TMDB_TOKEN}",
        @Path("movieId") movieId: Int
    ): Response<MovieDetailsResponse>

    @GET("movie/{movieId}/credits")
    suspend fun getCast(
        @Header("Authorization") token: String = "Bearer ${BuildConfig.TMDB_TOKEN}",
        @Path("movieId") movieId: Int
    ): Response<MovieCastResponse>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Header("Authorization") token: String = "Bearer ${BuildConfig.TMDB_TOKEN}",
        @Query("with_genres") genre: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

}