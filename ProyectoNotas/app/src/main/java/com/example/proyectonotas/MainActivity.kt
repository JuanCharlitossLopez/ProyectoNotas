package com.example.proyectonotas

/*import android.content.ClipData.Item*/

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectonotas.componentes.BottomBar
import com.example.proyectonotas.componentes.FAB
import com.example.proyectonotas.datos.NotasDAO
import com.example.proyectonotas.screens.PrincipalTareas


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
    Scaffold(
        topBar = { BottomBar() },
        content = { Content() },
        floatingActionButton = { FAB() }
    )
}
@Composable
fun NoteCardNOTAS(title: String, subtitle: String?, time: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 1.dp, vertical = 4.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(text = title, color = Color.Black)
            subtitle?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = subtitle, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = time, color = Color.Gray)
            }
        }
    }
}


@Composable
fun Content() {
    // Poner un lazy colum para cuando ponemos en landScape
    // LazyColumn(content = )
    //COLUMNA
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.secondary)
            .padding(12.dp)
            .verticalScroll(scrollState)  // Esto hace que el contenido sea desplazable

    ) {

        Row(
            modifier = Modifier
                .padding(50.dp)
                .offset(x = 10.dp)
                .offset(y = 30.dp)


        ) {
            Button(onClick = {
                ///NO
            }) {
                Text(text = "Notas", style = MaterialTheme.typography.titleLarge)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {
                val intent = Intent(context, PrincipalTareas::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Tareas", style = MaterialTheme.typography.titleLarge)

            }
        }
        NoteCardNOTAS(title = "Note 1", subtitle = "My day was wonderful", time = "Yesterday, 7:00 a.m.")
        NoteCardNOTAS(
            title = "Nota 2",
            subtitle = "I have to do all my homework",
            time = "Today, 6:00 p.m."
        )
        NoteCardNOTAS(title = "Nota 3", subtitle = "Need to pay the bills", time = "Today, 8:00 a.m.")
        NoteCardNOTAS(title = "Nota 4", subtitle = "Too bored", time = "Yesterday, 9:00 a.m.")
        NoteCardNOTAS(title = "Nota 5", subtitle = "Description", time = "Domingo, 10:00 a.m.")
        NoteCardNOTAS(title = "Nota 6", subtitle = "Description", time = "Sábado, 4:00 p.m.")


    }
}



