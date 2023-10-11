package com.example.proyectonotas.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Barra Baja de multimedia
@Preview
@Composable
fun BottomBar(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.White)
            .padding(15.dp, 10.dp, 10.dp, 5.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.List,
                contentDescription = "microfono",
                modifier = Modifier
                    .size(70.dp)
                    .padding(0.dp)
                    .offset(x = 320.dp)
                    .offset(y = (-5).dp)


            )}
    }
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Camara",
            modifier = Modifier
                .size(32.dp)
                .padding(0.dp)
                .offset(x = 45.dp)
                .offset(y = 5.dp)

        )}
    //Buttons multimedia
   /* IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = "Multimedia",
            modifier = Modifier
                .size(90.dp)
                .padding(0.dp)
                .offset(x = 1.dp)
                .offset(y = 5.dp)

        )}*/
    CajaDeTextoConContorno()



}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CajaDeTextoConContorno() {
    var texto by remember { mutableStateOf("") }

    OutlinedTextField(
        value = texto,
        onValueChange = { texto = it },
        label = { Text("Buscador") },
        modifier = Modifier
            .width(240.dp)  // Ajusta el ancho
            .height(50.dp)  // Ajusta el alto
            .padding(0.dp)
            .offset(x = 90.dp)

    )
}
