package com.satyamthakur.cinemate.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.satyamthakur.cinemate.R

val poppinsFont = FontFamily(
    Font(R.font.poppins_400_regular, FontWeight.Normal),
    Font(R.font.poppins_500_medium, FontWeight.Medium),
    Font(R.font.poppins_600_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_700_bold, FontWeight.Bold),
)

val labelTextMedium = TextStyle(
    fontFamily = poppinsFont,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    letterSpacing = 0.15.sp,
    color = primaryGrayForLabels
)

val bodyTextSB = TextStyle(
    fontFamily = poppinsFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    letterSpacing = 0.15.sp,
    color = Color.Black
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)