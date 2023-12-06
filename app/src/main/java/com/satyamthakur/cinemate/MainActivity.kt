package com.satyamthakur.cinemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
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
                            PopularNowSection()
                            TrendingCategorySection()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PopularNowSection () {
    Text(
        text = "Popular Now",
        fontFamily = poppinsFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = Color.Black,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    )

    PopularMoviesScreen()
}

@Composable
fun TrendingCategorySection () {
    Text(
        text = "Trending Category",
        fontFamily = poppinsFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = Color.Black,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    )
    TrendingCategories()
}

