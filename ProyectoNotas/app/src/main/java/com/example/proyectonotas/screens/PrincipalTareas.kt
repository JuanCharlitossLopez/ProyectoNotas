package com.example.proyectonotas.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.example.proyectonotas.componentes.FAB
import com.example.proyectonotas.componentes.ToolBar


@Composable
fun NoteCard(title: String, subtitle: String?, time: String) {
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
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Time", tint = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = time, color = Color.Gray )
            }
        }
    }
}

@Preview
@Composable
fun a() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(18.dp)

    ) {

        Row (
            modifier= Modifier
                .padding(16.dp)
                .offset(x = 70.dp)

        ){
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Notas", style =  MaterialTheme.typography.titleLarge)

            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Tareas", style =  MaterialTheme.typography.titleLarge)

            }


        }

       
        NoteCard(title = "Note 1", subtitle = "My day was wonderful", time = "Yesterday, 7:00 a.m.")
        NoteCard(title = "Nota 2", subtitle = "I have to do all my homework", time = "Today, 6:00 p.m.")
        NoteCard(title = "Nota 3", subtitle = "Need to pay the bills", time = "Today, 8:00 a.m.")
        NoteCard(title = "Nota 4", subtitle = "Too bored", time = "Yesterday, 9:00 a.m.")
        NoteCard(title = "Nota 5", subtitle = "Description", time = "Domingo, 10:00 a.m.")
        NoteCard(title = "Nota 6", subtitle = "Description", time = "Sábado, 4:00 p.m.")



    }
}
