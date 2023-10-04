
package com.example.proyectonotas

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectonotas.componentes.BottomBar
import com.example.proyectonotas.componentes.FAB
import com.example.proyectonotas.componentes.SearchBar
import com.example.proyectonotas.componentes.ToolBar
import com.example.proyectonotas.screens.CardView
import com.example.proyectonotas.screens.ListCard
import com.example.proyectonotas.screens.ReminderCard
import com.example.proyectonotas.screens.TextCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewContainer() //manda llamar nuestra aplicacion
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ViewContainer() {
    Scaffold (
       topBar = { ToolBar() },
        content = { Content()},

        bottomBar = {BottomBar()}//poner la barra de abajo
        //floatingActionButton = { FAB() }
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
            .background(Color.Gray)
            .padding(top = 160.dp)
    )
    {/*
        TextCard(title = "Material Design", content = "Material design is a foundation upon which applications for Google platforms are built. These principles are intended for a wide audience.")
        TextCard(title = "Crepe Recipe", content = "1. In a blender, combine flour, sugar, salt, milk, eggs, and butter.\n2. Puree until mixture is smooth and bubbles form on top, about 30 seconds. Let batter sit.")
        ListCard(title = "Grocery list", items = listOf("Paper towels", "Kale", "Tomatoes", "Bread", "Avocado", "Olive Oil", "Tofu", "Pepper"))
        ListCard(title = "Surprise party for Kristin!", items = listOf())
        ListCard(title = "Skiing prep", items = listOf("Googles", "Gloves", "Helmet", "Jacket", "Pants", "Skis", "Boots", "Slippers"))
        TextCard(title = "1175 Borregas Ave", content = "Sunnyvale, CA 94089")*/
        Text(text = "Recordatorios próximos", color = Color.Black, modifier = Modifier.padding(bottom = 8.dp))
        ReminderCard(title = "Hola 1", subtitle = null, time = "Mañana, 8:00 a.m.")
        ReminderCard(title = "Hola 3", subtitle = null, time = "Mañana, 8:00 a.m.")
        ReminderCard(title = "Hola", subtitle = "Me", time = "Mañana, 8:00 a.m.")
    }
}



