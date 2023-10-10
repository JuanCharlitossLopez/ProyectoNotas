package com.example.proyectonotas.componentes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

@Composable
fun MenuBar() {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = { /* TODO: Handle "Notas" click */ },
            //colors = ButtonDefaults.buttonColors(background = Color.Black, contentColor = Color.White),
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(text = "Notas")
        }

        Button(
            onClick = { /* TODO: Handle "Tareas" click */ },
            //colors = ButtonDefaults.buttonColors(background = Color.White, contentColor = Color.Black),
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "Tareas")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuBar() {
    MenuBar()
}