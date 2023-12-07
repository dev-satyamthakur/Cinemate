package com.satyamthakur.cinemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.satyamthakur.cinemate.screens.MovieDetailsScreen
import com.satyamthakur.cinemate.screens.MoviesByGenreScreen
import com.satyamthakur.cinemate.screens.PopularMoviesScreen
import com.satyamthakur.cinemate.screens.TrendingCategories
import com.satyamthakur.cinemate.ui.theme.CinemateTheme
import com.satyamthakur.cinemate.ui.theme.poppinsFont
import com.satyamthakur.cinemate.utils.NetworkChecker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CinemateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(
                navigateToMovieDetails = { navController.navigate("movie_details/${it}") },
                navigateToGenreMovies = { navController.navigate("genre_movies/${it}") }
            )
        }
        composable(route = "movie_details/{movieId}", arguments = listOf(
            navArgument("movieId") {
                type = NavType.StringType
            }
        )) {
            MovieDetails(navController) // passing navController for back navigation
        }
        composable(route = "genre_movies/{genreId}", arguments = listOf(
            navArgument("genreId") {
                type = NavType.StringType
            }
        )) {
            MoviesByGenre(navController)
        }
    }
}

@Composable
fun MoviesByGenre(navController: NavController) {
    MoviesByGenreScreen(navController)
}


@Composable
fun MovieDetails(navController: NavController) {
    MovieDetailsScreen(navController)
}

@Composable
fun HomeScreen(
    navigateToMovieDetails: (movieId: String) -> Unit,
    navigateToGenreMovies: (genreId: String) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "CINEMATE",
            fontFamily = poppinsFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        if (!NetworkChecker.isNetworkAvailable(LocalContext.current)) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No Internet Connectivity")
            }
        } else {
            PopularNowSection(navigateToMovieDetails)
            TrendingCategorySection(navigateToGenreMovies)
        }
    }
}

@Composable
fun PopularNowSection(navigateToMovieDetails: (movieId: String) -> Unit) {
    Text(
        text = "Popular Now",
        fontFamily = poppinsFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = Color.Black,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    )

    PopularMoviesScreen(navigateToMovieDetails)
}

@Composable
fun TrendingCategorySection(navigateToGenreMovies: (genreId: String) -> Unit) {
    Text(
        text = "Trending Category",
        fontFamily = poppinsFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = Color.Black,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    )
    TrendingCategories(navigateToGenreMovies)
}

