package com.example.proyectonotas.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            .height(50.dp)
            .background(Color.White)
            .padding(15.dp, 10.dp, 10.dp, 5.dp)
    ) {
        //Buttons microfono
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = "microfono",
                modifier = Modifier
                    .size(32.dp)
                    .padding(0.dp)
                    .offset(x = 320.dp)
            )}
    } //Buttons Camara
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Camara",
            modifier = Modifier
                .size(32.dp)
                .padding(0.dp)
                .offset(x = 160.dp)
        )}
    //Buttons multimedia
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "Multimedia",
            modifier = Modifier
                .size(32.dp)
                .padding(0.dp)
                .offset(x = 2.dp)
        )}



}