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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment


@Composable
fun ReminderCard(title: String, subtitle: String?, time: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
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
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text(text = "Recordatorios próximos", color = Color.Black, modifier = Modifier.padding(bottom = 8.dp))
        ReminderCard(title = "Hola 1", subtitle = null, time = "Mañana, 8:00 a.m.")
        ReminderCard(title = "Hola 3", subtitle = null, time = "Mañana, 8:00 a.m.")
        ReminderCard(title = "Hola", subtitle = "Me", time = "Mañana, 8:00 a.m.")
    }
}
