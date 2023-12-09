package com.satyamthakur.cinemate.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.viewmodels.MoviesNowPlayingViewModel

@Composable
fun NewReleasedMovies (navigateToMovieDetails: (movieId: String) -> Unit) {
    val nowPlayingMoviesViewModel: MoviesNowPlayingViewModel = hiltViewModel()
    val movies: State<List<Result>> = nowPlayingMoviesViewModel.movies.collectAsState()

    if (movies.value.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth().height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )
        }
    } else {
        ShowNowPlayingMovies(movies = movies.value, navigateToMovieDetails)
    }
}

@Composable
fun ShowNowPlayingMovies (
    movies: List<Result>,
    navigateToMovieDetails: (movieId: String) -> Unit
) {
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues( // Adding padding to first and last card
            start = 16.dp,
            end = 16.dp
        ),
        modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
    ) {
        items(movies) { item ->
            MovieCardItemsNowPlaying(item, navigateToMovieDetails)
        }
    }
}

@Composable
fun MovieCardItemsNowPlaying(movie: Result, navigateToMovieDetails: (movieId: String) -> Unit) {
    Card(
        modifier = Modifier.width(120.dp).height(180.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/original${movie.poster_path}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().clickable {
                navigateToMovieDetails(movie.id.toString())
            }
        )
    }
}