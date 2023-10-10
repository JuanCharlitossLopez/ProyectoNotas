
package com.example.proyectonotas

import android.annotation.SuppressLint
/*import android.content.ClipData.Item*/
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectonotas.componentes.BottomBar
import com.example.proyectonotas.componentes.FAB
import com.example.proyectonotas.componentes.SearchBar

import com.example.proyectonotas.screens.NoteCard


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
        topBar = { BottomBar() },
        content = { Content()},

        floatingActionButton = { FAB() }
    )
}



@Composable
fun Content() {
    // Poner un lazy colum para cuando ponemos en landScape
    // LazyColumn(content = )
    //COLUMNA
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(19.dp)
            .verticalScroll(scrollState)  // Esto hace que el contenido sea desplazable
            .padding(18.dp)

    ) {

        Row(
            modifier = Modifier
                .padding(50.dp)
                .offset(x = 20.dp)
                .offset(y = 30.dp)
                .offset(x = 15.dp)

        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Notas", style = MaterialTheme.typography.titleLarge)

            }
            Spacer(modifier = Modifier.width(14.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Tareas", style = MaterialTheme.typography.titleLarge)

            }
        }
        NoteCard(title = "Note 1", subtitle = "My day was wonderful", time = "Yesterday, 7:00 a.m.")
        NoteCard(
            title = "Nota 2",
            subtitle = "I have to do all my homework",
            time = "Today, 6:00 p.m."
        )
        NoteCard(title = "Nota 3", subtitle = "Need to pay the bills", time = "Today, 8:00 a.m.")
        NoteCard(title = "Nota 4", subtitle = "Too bored", time = "Yesterday, 9:00 a.m.")
        NoteCard(title = "Nota 5", subtitle = "Description", time = "Domingo, 10:00 a.m.")
        NoteCard(title = "Nota 6", subtitle = "Description", time = "Sábado, 4:00 p.m.")


    }
}



