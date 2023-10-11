package com.example.proyectonotas.screens

import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.ui.platform.LocalContext
import com.example.proyectonotas.MainActivity


class PrincipalNotas : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyEmptyScreen()
        }
    }
}

@Composable
fun MyEmptyScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
    ){
        CustomTopBar() // La TopBar que creamos anteriormente
        editarTitulo()
        editarSubTitulo()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyEmptyScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopBar()
        editarTitulo()
        editarSubTitulo()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

        }
    }}
@Preview
@Composable
fun CustomTopBar() {
    val context = LocalContext.current
    val currentDate = remember { getCurrentDate() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp) // Altura típica para una TopAppBar
            .background(Color.LightGray), //  color
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás")
        }

        Text(text = "Modificado: $currentDate", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        IconButton(onClick = { /* Acción para el ícono de menú */ }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menú")
        }
    }
}

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editarTitulo() {
    var textState by remember { mutableStateOf("") }

    TextField(
        value = textState,
        onValueChange = { textState = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        ),
        placeholder = {
            Text(text = "TIME", fontSize = 48.sp, fontWeight = FontWeight.Bold)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editarSubTitulo() {
    var textState by remember { mutableStateOf("") }

    TextField(
        value = textState,
        onValueChange = { textState = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 16.sp
        ),
        placeholder = {
            Text(text = "Texto más pequeño aquí", fontSize = 16.sp)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun EditableFieldsPreview() {
    Column {
        editarTitulo()
        editarSubTitulo()
    }
}