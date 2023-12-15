package com.satyamthakur.cinemate.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.respository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesByGenreViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var currPage = 1

    val movies: StateFlow<List<Result>>
        get() = repository.moviesByGenre

    init {
        viewModelScope.launch {
            val genreId = savedStateHandle.get<String>("genreId") ?: "28"
            repository.getMoviesByGenre(genreId, currPage++)
        }
    }

    fun getMoreMovies() {
        viewModelScope.launch {
            val genreId = savedStateHandle.get<String>("genreId") ?: "28"
            repository.getMoviesByGenre(genreId, currPage++)
        }
    }

}