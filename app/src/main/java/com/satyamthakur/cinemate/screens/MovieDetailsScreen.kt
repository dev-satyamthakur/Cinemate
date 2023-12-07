package com.satyamthakur.cinemate.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.satyamthakur.cinemate.R
import com.satyamthakur.cinemate.models.MovieCastResponse
import com.satyamthakur.cinemate.models.MovieDetailsResponse
import com.satyamthakur.cinemate.models.Result
import com.satyamthakur.cinemate.ui.theme.bodyTextSB
import com.satyamthakur.cinemate.ui.theme.labelTextMedium
import com.satyamthakur.cinemate.ui.theme.poppinsFont
import com.satyamthakur.cinemate.ui.theme.primaryGrayForLabels
import com.satyamthakur.cinemate.utils.LanguageMapper
import com.satyamthakur.cinemate.utils.TimeConvertorUtility
import com.satyamthakur.cinemate.utils.data.exampleMovieDetails
import com.satyamthakur.cinemate.viewmodels.MovieCastViewModel
import com.satyamthakur.cinemate.viewmodels.MovieDetailsViewModel
import com.satyamthakur.cinemate.viewmodels.MoviesViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(navController: NavController) {
    val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
    val movie: State<MovieDetailsResponse?> =
        movieDetailsViewModel.movieDetails.collectAsState()

    if (movie.value == null) {
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
        MovieDetailTopAppBar(movie = movie.value!!, navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailTopAppBar(movie: MovieDetailsResponse, navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        text = movie.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = poppinsFont,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
//                actions = {
//                    IconButton(onClick = { /* do something */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Localized description"
//                        )
//                    }
//                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Surface(
            color = Color.White
        ) {
            MovieDetail(movie, innerPadding)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Prev() {
    MovieDetail(exampleMovieDetails, innerPadding = PaddingValues(16.dp))
}

@Composable
fun MovieDetail(movie: MovieDetailsResponse, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
            .padding(innerPadding),
    ) {
        Card(
            modifier = Modifier
                .padding(top = 16.dp)
                .height(300.dp)
                .width(220.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original${movie.posterPath}",
                placeholder = painterResource(R.drawable.placeholder_loading),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = movie.title,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            lineHeight = 24.sp,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp, end = 32.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = ((movie.voteAverage * 100.0).roundToInt() / 100.0).toString(),
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color(0xFF717171),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
                .align(Alignment.CenterHorizontally)
        )

        RatingStars(movie.voteAverage)

        MovieBasicDetails(movie = movie)

        MovieOverview(movie.overview)

        MoviesCastDetails()
    }
}

@Composable
fun MovieOverview(movieOverview: String) {
    Column(
        modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "Storyline",
            fontFamily = poppinsFont,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        var isExpanded by remember { mutableStateOf(false) }

        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Color.White,
            // animateContentSize will change the Surface size gradually
            modifier = Modifier.animateContentSize().padding(1.dp)
        ) {
            Column {
                Text(
                    text = movieOverview,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = primaryGrayForLabels,
                    modifier = Modifier.padding(top = 8.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 4,
                )
                if (!isExpanded) {
                    Text(
                        text = "[Read More]",
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(top = 2.dp).align(Alignment.End).clickable {
                            isExpanded = !isExpanded
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MovieBasicDetails(movie: MovieDetailsResponse) {
    Row(
        modifier = Modifier
            .padding(top = 24.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.width(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Length",
                style = labelTextMedium
            )
            Text(
                TimeConvertorUtility.convertMinutesToTimeString(movie.runtime),
                style = bodyTextSB,
            )
        }

        Divider(
            modifier = Modifier.height(30.dp).width(1.dp),
            color = primaryGrayForLabels
        )

        Column(
            modifier = Modifier.width(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Language",
                style = labelTextMedium
            )
            Text(
                LanguageMapper.getEnglishName(movie.originalLanguage).toString(),
                style = bodyTextSB,
            )

        }

        Divider(
            modifier = Modifier.height(30.dp).width(1.dp),
            color = primaryGrayForLabels
        )

        Column(
            modifier = Modifier.width(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Year",
                style = labelTextMedium
            )
            Text(
                movie.releaseDate.split("-")[0],
                style = bodyTextSB,
            )
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
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(fullCount) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = Color(0xffe8b923),
                modifier = Modifier.padding(2.dp).size(18.dp)
            )
        }
        if (voteAverage >= 0.5) {
            Icon(
                painter = painterResource(R.drawable.ic__star_half),
                contentDescription = null,
                tint = Color(0xffe8b923),
                modifier = Modifier.padding(2.dp).size(18.dp)
            )
        }
    }
}