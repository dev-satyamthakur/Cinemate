package com.satyamthakur.cinemate.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyamthakur.cinemate.models.MovieDetailsResponse
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.respository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val movieDetails: StateFlow<MovieDetailsResponse?>
        get() = repository.movieDetails

    init {
        viewModelScope.launch {
            val movieId = savedStateHandle.get<String>("movieId") ?: "901362"
            repository.getMovieDetails(movieId = movieId.toInt())
        }
    }

}