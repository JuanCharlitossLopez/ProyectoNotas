package com.example.proyectonotas.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CardView(title: String, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Column {
            Text(text = title, color = Color.Black, modifier = Modifier.padding(bottom = 8.dp))
            content()
        }
    }
}

@Composable
fun TextCard(title: String, content: String) {
    CardView(title = title) {
        Text(text = content, color = Color.Black)
    }
}

@Composable
fun ListCard(title: String, items: List<String>) {
    CardView(title = title) {
        LazyColumn {
            items(items) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = false, onCheckedChange = null, modifier = Modifier.padding(end = 8.dp))
                    Text(text = item, color = Color.Black)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewInterface() {
    Column {
        TextCard(title = "Material Design", content = "Material design is a foundation upon which applications for Google platforms are built. These principles are intended for a wide audience.")
        TextCard(title = "Crepe Recipe", content = "1. In a blender, combine flour, sugar, salt, milk, eggs, and butter.\n2. Puree until mixture is smooth and bubbles form on top, about 30 seconds. Let batter sit.")
        ListCard(title = "Grocery list", items = listOf("Paper towels", "Kale", "Tomatoes", "Bread", "Avocado", "Olive Oil", "Tofu", "Pepper"))
        ListCard(title = "Surprise party for Kristin!", items = listOf())
        ListCard(title = "Skiing prep", items = listOf("Googles", "Gloves", "Helmet", "Jacket", "Pants", "Skis", "Boots", "Slippers"))
        TextCard(title = "1175 Borregas Ave", content = "Sunnyvale, CA 94089")
    }
}