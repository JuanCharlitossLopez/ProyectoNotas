package com.example.proyectonotas.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(onClick = { /* TODO: Handle search action */ }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text(text = "Search", fontSize = 20.sp) }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchBar() {
    SearchBar(value = TextFieldValue(""), onValueChange = {})
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarSearch(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(Color.LightGray)
        //.align(alignment = Alignment.CenterHorizontally)
        //.height(100.dp)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                )
            }, placeholder = { Text(text = "Buscar") },

            //.offset(15.dp, -12.dp)

        )

    }
}

//preview
@Preview
@Composable
fun Myapp() {
    var searchText by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
    )
    {
        BarSearch(value = searchText, onValueChange = { newText -> searchText = newText })
    }
}*/




