package com.satyamthakur.cinemate.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.satyamthakur.cinemate.R
import com.satyamthakur.cinemate.models.MovieDetailsResponse
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.ui.theme.poppinsFont
import com.satyamthakur.cinemate.viewmodels.MovieDetailsViewModel
import com.satyamthakur.cinemate.viewmodels.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen() {
    val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
    val movie: State<MovieDetailsResponse?> =
        movieDetailsViewModel.movieDetails.collectAsState()

    if (movie.value == null) {
        Text("Loading...")
    } else {

        MovieDetail(movie.value!!)

    }
}

@Composable
fun MovieDetail(movie: MovieDetailsResponse) {
    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Card(
            modifier = Modifier
                .padding(top = 32.dp)
                .height(320.dp)
                .width(250.dp)
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original${movie.posterPath}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = movie.title,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp, start = 32.dp, end = 32.dp)
        )

        Text(
            text = movie.voteAverage.toString(),
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            color = Color(0xFF717171),
            modifier = Modifier.padding()
        )

        RatingStars(movie.voteAverage)
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