package com.example.proyectonotas.componentes

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectonotas.R

//ENCABEZADO DE LA APLICACION
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar() {
    TopAppBar(
        title = { Text(text = "ITSUR", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
        Modifier.background(colorResource(id = R.color.purple_200))
    )
}