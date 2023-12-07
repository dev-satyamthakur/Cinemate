package com.satyamthakur.cinemate.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.satyamthakur.cinemate.models.MovieDetailsResponse
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.viewmodels.MovieDetailsViewModel
import com.satyamthakur.cinemate.viewmodels.MoviesByGenreViewModel

@Composable
fun MoviesByGenreScreen(navController: NavController) {
    val moviesByGenreViewModel: MoviesByGenreViewModel = hiltViewModel()
    val movies: State<List<Result>> =
        moviesByGenreViewModel.movies.collectAsState()

    if (movies.value.isEmpty()) {
        Text("Loading...")
    } else {
        LazyColumn {
            items(movies.value.count()) {
                Text(text = movies.value.get(it).title)
            }
        }
    }
}