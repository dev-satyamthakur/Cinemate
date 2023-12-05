package com.satyamthakur.cinemate.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.satyamthakur.cinemate.R
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.ui.theme.poppinsFont
import com.satyamthakur.cinemate.utils.GenreMapper
import com.satyamthakur.cinemate.viewmodels.MoviesViewModel

@Composable
fun NowPlayingMoviesScreen() {
    val moviesViewModel: MoviesViewModel = hiltViewModel()
    val movies: State<List<Result>> = moviesViewModel.movies.collectAsState()

    if (movies.value.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )
        }
    } else {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            // ...
            this.items(movies.value) {
                MovieItem(movieResult = it)
            }
        }
    }
}

@Composable
fun MovieItem(movieResult: Result) {
    Column(
        modifier = Modifier.width(300.dp)
    ) {
        ImageCard(
            painter = "https://image.tmdb.org/t/p/w500${movieResult.backdrop_path}",
            title = movieResult.title,
            movieResult = movieResult
        )
    }
}

@Composable
fun ImageCard(
    painter: String,
    title: String,
    movieResult: Result,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier.height(200.dp)
        ) {
            AsyncImage(
                model = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = title,
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        fontSize = 20.sp
                    )

                    var genres = GenreMapper.mapIdsToGenreNames(movieResult.genre_ids)
                    Row {
                        for (i in 0..minOf(2, genres.size - 1)) {
                            Text(
                                genres.get(i) + if (i == 2 || i == genres.lastIndex) "" else ",",
                                modifier = Modifier.padding(2.dp),
                                color = Color.White,
                                fontFamily = poppinsFont,
                                fontSize = 12.sp
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun RatingStars(votes: Double) {
    var voteAverage = votes
    voteAverage = voteAverage / 2
    var fullCount = voteAverage.toInt()
    voteAverage = voteAverage - fullCount.toDouble()

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        repeat(fullCount) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = Color(0xffe8b923),
                modifier = Modifier.size(18.dp)
            )
        }
        if (voteAverage >= 0.5) {
            Icon(
                painter = painterResource(R.drawable.ic__star_half),
                contentDescription = null,
                tint = Color(0xffe8b923),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun NormalMovieCard(poster_path: String) {
    Card(
        modifier = Modifier.fillMaxWidth().height(300.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${poster_path}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}