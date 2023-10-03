@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.proyectonotas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectonotas.componentes.BarSearch
import com.example.proyectonotas.componentes.FAB
import com.example.proyectonotas.componentes.ToolBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewContainer() //manda llamar nuestra aplicacion
        }
    }
}

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewContainer() {
    Scaffold (
        //topBar = { ToolBar()},
        content = { Content()},
        floatingActionButton = { FAB() }
    )
}


@Composable
fun Content() {
    // Poner un lazy colum para cuando ponemos en landScape
    // LazyColumn(content = )
    //COLUMNA
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(0.dp,30.dp,0.dp,0.dp)
    )
    {
        BarSearch()
    }
}



