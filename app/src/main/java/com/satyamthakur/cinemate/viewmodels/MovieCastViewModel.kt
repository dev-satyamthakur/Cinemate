package com.satyamthakur.cinemate.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyamthakur.cinemate.models.MovieCastResponse
import com.satyamthakur.cinemate.respository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCastViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val cast: StateFlow<List<MovieCastResponse.Cast>>
        get() = repository.movieCast

    init {
        viewModelScope.launch {
            val movieId = savedStateHandle.get<String>("movieId") ?: "901362"
            repository.getMovieCast(movieId.toInt())
        }
    }
}