package com.example.proyectonotas.componentes

import android.content.Intent
import android.widget.Toast
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectonotas.screens.PrincipalNotas

//BOTON FlONTANTE
@Preview
@Composable
fun FAB() {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = {
            //screens.PrincipalNotas" />
            val intent = Intent(context, PrincipalNotas::class.java)
            context.startActivity(intent)
        })
    {
        Text(text = "+")
    }
}
