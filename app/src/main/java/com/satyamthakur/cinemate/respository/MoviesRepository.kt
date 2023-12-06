package com.satyamthakur.cinemate.respository

import androidx.compose.ui.platform.LocalContext
import com.satyamthakur.cinemate.api.CineAPI
import com.satyamthakur.cinemate.models.MovieDetailsResponse
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.utils.NetworkChecker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val cineAPI: CineAPI) {

    private val _movies = MutableStateFlow<List<Result>>(emptyList())
    val movies: StateFlow<List<Result>>
        get() = _movies

    private val _movieDetails = MutableStateFlow<MovieDetailsResponse?>(null)
    val movieDetails: StateFlow<MovieDetailsResponse?>
        get() = _movieDetails

    suspend fun getMovies(currPage: Int) {

        val response = cineAPI.getPopularMovies(what = "popular", page = currPage)
        if (response.isSuccessful && response.body() != null) {

            var newlist = mutableListOf<Result>()
            newlist.addAll(_movies.value)
            newlist.addAll(response.body()!!.results)

            var mylist: List<Result> = newlist

            _movies.emit(mylist)
        }
    }

    suspend fun getMovieDetails(movieId: Int) {
        val response = cineAPI.getMovieDetails(movieId = movieId)
        if (response.isSuccessful && response.body() != null) {
            _movieDetails.emit(response.body())
        }
    }

}