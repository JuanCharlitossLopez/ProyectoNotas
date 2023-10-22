package com.example.proyectonotas.componentes

import android.content.Intent
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectonotas.screens.AddTask

@Preview
@Composable
fun FABt() {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = {
            val intent = Intent(context, AddTask::class.java)
            context.startActivity(intent)
        })
    {
        Text(text = "-")
    }
}