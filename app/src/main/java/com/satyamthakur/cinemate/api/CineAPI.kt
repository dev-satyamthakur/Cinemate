package com.satyamthakur.cinemate.api

import com.satyamthakur.cinemate.BuildConfig
import com.satyamthakur.cinemate.models.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CineAPI {

    @GET("movie/{what}")
    suspend fun getPopularMovies(
        @Header("Authorization") token: String = "Bearer ${BuildConfig.TMDB_TOKEN}",
        @Path("what") what: String,
        @Query("region") region: String = "",
        @Query("page") page: Int
        ): Response<PopularMoviesResponse>

}