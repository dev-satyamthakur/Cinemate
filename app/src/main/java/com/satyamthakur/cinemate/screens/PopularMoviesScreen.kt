package com.satyamthakur.cinemate.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.ui.theme.poppinsFont
import com.satyamthakur.cinemate.utils.GenreMapper
import com.satyamthakur.cinemate.viewmodels.MoviesPopularViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun PopularMoviesScreen(navigateToMovieDetails: (movieId: String) -> Unit) {

    val moviesPopularViewModel: MoviesPopularViewModel = hiltViewModel()
    val movies: State<List<Result>> = moviesPopularViewModel.movies.collectAsState()

    if (movies.value.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth().height(180.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )
        }
    } else {
        val listState = rememberLazyListState(Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2) % movies.value.size)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 16.dp),
            contentPadding = PaddingValues( // Adding padding to first and last card
                start = 16.dp,
                end = 16.dp
            ),
            state = listState
        ) {
            items(Int.MAX_VALUE, itemContent = {
                val index = it % movies.value.size
                MovieItem(movieResult = movies.value[index], navigateToMovieDetails)    // item composable
            })
        }
    }

}

@Composable
fun MovieItem(movieResult: Result, navigateToMovieDetails: (movieId: String) -> Unit) {
    Column(
        modifier = Modifier.width(300.dp)
    ) {
        ImageCard(
            painter = "https://image.tmdb.org/t/p/original${movieResult.backdrop_path}",
            title = movieResult.title,
            movieResult = movieResult,
            navigateToMovieDetails = navigateToMovieDetails
        )
    }
}

@Composable
fun ImageCard(
    painter: String,
    title: String,
    movieResult: Result,
    modifier: Modifier = Modifier,
    navigateToMovieDetails: (movieId: String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .height(180.dp)
                .clickable {
                    navigateToMovieDetails(movieResult.id.toString())
                }
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
                                Color(0xFF292929)
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
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 16.sp,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        )
                    )

                    var genres = GenreMapper.mapIdsToGenreNames(movieResult.genre_ids)

                    Row {
                        for (i in 0..minOf(2, genres.size - 1)) {
                            Text(
                                text = genres.get(i) + if (i == 2 || i == genres.lastIndex) "" else ", ",
                                color = Color.White,
                                fontFamily = poppinsFont,
                                fontSize = 12.sp,
                                style = TextStyle(
                                    platformStyle = PlatformTextStyle(
                                        includeFontPadding = false
                                    )
                                )
                            )
                        }
                    }
                }
            }
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
            model = "https://image.tmdb.org/t/p/original${poster_path}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}