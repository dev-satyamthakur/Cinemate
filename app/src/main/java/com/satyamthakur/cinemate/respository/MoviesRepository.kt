package com.satyamthakur.cinemate.respository

import com.satyamthakur.cinemate.api.CineAPI
import com.satyamthakur.cinemate.models.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val cineAPI: CineAPI) {

    private val _movies = MutableStateFlow<List<Result>>(emptyList())
    val movies: StateFlow<List<Result>>
        get() = _movies


    suspend fun getMovies() {
        val response = cineAPI.getPopularMovies(what = "now_playing")
        if (response.isSuccessful && response.body() != null) {
            _movies.emit(response.body()!!.results)
        }
    }

}