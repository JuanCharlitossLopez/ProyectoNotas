package com.example.proyectonotas.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proyectonotas.R

private val kanit = FontFamily(
       Font(R.font.kanit_regular, FontWeight.Normal),
       Font(R.font.kanit_medium, FontWeight.Medium),
       Font(R.font.kanit_semibold, FontWeight.SemiBold),
       Font(R.font.kanit_extralight, FontWeight.ExtraLight),
       Font(R.font.kanit_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
          headlineLarge = TextStyle(
          fontFamily = kanit,
          fontWeight = FontWeight.SemiBold,
          fontSize = 15.sp
        ),

        bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
        bodySmall = TextStyle(
        fontFamily = kanit,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
)

)