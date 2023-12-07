package com.satyamthakur.cinemate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.satyamthakur.cinemate.R
import com.satyamthakur.cinemate.models.MovieCastResponse
import com.satyamthakur.cinemate.ui.theme.bodyTextSB
import com.satyamthakur.cinemate.ui.theme.labelTextMedium
import com.satyamthakur.cinemate.ui.theme.poppinsFont
import com.satyamthakur.cinemate.viewmodels.MovieCastViewModel

@Composable
fun MoviesCastDetails () {
    val moviesCast: MovieCastViewModel = hiltViewModel()
    val cast: State<List<MovieCastResponse.Cast>> = moviesCast.cast.collectAsState()

    Column(
        modifier = Modifier.padding(top = 24.dp)
    ) {
        Text(
            text = "Cast",
            fontFamily = poppinsFont,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, end = 8.dp)
        )
        if (cast.value.isEmpty()) {
            Box(
                modifier = Modifier.height(100.dp).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black
                )
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues( // Adding padding to first and last card
                    start = 16.dp,
                    end = 16.dp
                ),
            ) {
                items(cast.value.count()) { item ->
                    CastItem(cast.value.get(item))
                }
            }
        }
    }

}

@Composable
fun CastItem (cast: MovieCastResponse.Cast) {
    Column(
        modifier = Modifier.padding(bottom = 16.dp).width(120.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(top = 16.dp)
                .height(120.dp)
                .width(120.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original${cast.profilePath}",
                placeholder = painterResource(R.drawable.placeholder_loading),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = cast.name,
            style = bodyTextSB,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = cast.character,
            style = labelTextMedium,
            fontSize = 11.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}