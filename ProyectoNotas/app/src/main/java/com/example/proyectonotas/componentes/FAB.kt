package com.example.proyectonotas.componentes

import android.widget.Toast
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

//BOTON FlONTANTE
@Composable
fun FAB() {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = { Toast.makeText(context, "Hola", Toast.LENGTH_SHORT).show() })
    {
        Text(text = "+")
    }
}