package com.satyamthakur.cinemate.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.respository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesPopularViewModel @Inject constructor(private val repository: MoviesRepository): ViewModel() {

    private var currPage = 1

    val movies: StateFlow<List<Result>>
        get() = repository.movies

    init {
        viewModelScope.launch {
            repository.getMovies(currPage++)
        }
    }

    fun getMoreMovies() {
        viewModelScope.launch {
            repository.getMovies(currPage++)
        }
    }
}