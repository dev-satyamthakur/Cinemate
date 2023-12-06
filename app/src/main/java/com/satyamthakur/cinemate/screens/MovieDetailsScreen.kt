package com.satyamthakur.cinemate.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.satyamthakur.cinemate.models.MovieDetailsResponse
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.viewmodels.MovieDetailsViewModel
import com.satyamthakur.cinemate.viewmodels.MoviesViewModel

@Composable
fun MovieDetailsScreen() {
    val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
    val movieDetails: State<MovieDetailsResponse?> =
        movieDetailsViewModel.movieDetails.collectAsState()

    if (movieDetails.value == null) {
        Text("Loading...")
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = movieDetails.value!!.title
            )
        }
    }
}