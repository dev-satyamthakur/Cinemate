package com.satyamthakur.cinemate.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyamthakur.cinemate.ui.theme.poppinsFont

@Composable
fun TrendingCategories(navigateToGenreMovies: (genreId: String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                GenreCard("Action", Color(0xFFFFC000), navigateToGenreMovies)
            }
            Box(
                modifier = Modifier.weight(1f)
            ) {
                GenreCard("Fantasy", Color(0xFFff9627), navigateToGenreMovies)
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                GenreCard("Adventure", Color(0xFF70bba3), navigateToGenreMovies)
            }
            Box(
                modifier = Modifier.weight(1f)
            ) {
                GenreCard("Sci-Fi", Color(0xFFed2e45), navigateToGenreMovies)
            }
        }
    }
}

@Composable
fun GenreCard(
    name: String,
    cardContainerColor: Color,
    navigateToGenreMovies: (genreId: String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardContainerColor
        ),
        modifier = Modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                           when (name) {
                               "Action" -> navigateToGenreMovies("28")
                               "Fantasy" -> navigateToGenreMovies("14")
                               "Adventure" -> navigateToGenreMovies("12")
                               "Sci-Fi" -> navigateToGenreMovies("878")
                               else -> navigateToGenreMovies("28")
                           }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                color = Color.White,
                fontFamily = poppinsFont,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}