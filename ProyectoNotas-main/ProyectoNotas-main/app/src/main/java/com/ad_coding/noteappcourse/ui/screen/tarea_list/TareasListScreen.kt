package com.ad_coding.noteappcourse.ui.screen.tarea_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.EventNote
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.ad_coding.noteappcourse.domain.model.Tarea
import com.ad_coding.noteappcourse.ui.util.Route
import com.ad_coding.noteappcourse.window.infoWindow
import com.ad_coding.noteappcourse.window.rememberWindow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareaListScreen(
    tareaList: List<Tarea>,
    onTareaClick: (Tarea) -> Unit,
    onAddTareaClick: () -> Unit
) {
    val navController = rememberNavController()
    var searchText by remember { mutableStateOf(TextFieldValue()) }
    var filteredTareas by remember { mutableStateOf(tareaList) }
    var searchResultText by remember { mutableStateOf("") }

    // Inicializar filteredNotes con todas las notas al principio
    filteredTareas = tareaList
    val windowInfo = rememberWindow()//Para detectar el tipo de pantalla
    var sizeicon = 0
    //PANTALLA PEQUEÑA
    if (windowInfo.windowHeightInfo is infoWindow.tipoPantalla.Compact) {
        sizeicon = 15
    }
    //PANTALLA MEDIANA
    if (windowInfo.windowHeightInfo is infoWindow.tipoPantalla.Medium) {
        sizeicon = 32
    }
    //PANTALLA GRANDE
    if (windowInfo.windowHeightInfo is infoWindow.tipoPantalla.Expanded) {
        sizeicon = 48
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    BasicTextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            filteredTareas = filterTareas(tareaList, it.text)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(
                                vertical = 11.dp
                            ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,  // Tamaño de fuente personalizado
                            fontWeight = FontWeight.Bold,  // Peso de la fuente
                            color = Color.Black  // Color del texto
                        )
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            // Puedes implementar aquí la lógica de búsqueda
                            // utilizando el valor de "searchText"
                            filteredTareas = filterTareas(tareaList, searchText.text)
                            if (filteredTareas.isNotEmpty()) {
                                searchResultText = "Resultados encontrados"
                            } else {
                                searchResultText = "No se encontraron resultados"
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            modifier = Modifier.size(sizeicon.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTareaClick
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Agregar tarea"
                )
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 15.dp + padding.calculateTopPadding(),
                bottom = 15.dp + padding.calculateBottomPadding()
            )
        ) {
            // Agregar dos botones en el centro de la pantalla
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            //navController.navigate(Route.noteList)
                        }
                    ) {
                        Text("Notas")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            // Lógica del segundo botón

                        }
                    ) {
                        Text("Tareas")
                    }
                }
            }
            item {
                Text(
                    text = "Tareas",
                    style = MaterialTheme.typography.titleLarge

                )
            }
            items(filteredTareas) { tarea ->
                ListItem(
                    headlineText = {
                        Text(
                            text = tarea.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    supportingText = {
                        Text(text = tarea.content, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        Icon(imageVector = Icons.Rounded.EventNote,contentDescription = "")
                        Text(text = tarea.date, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    },
                    modifier = Modifier.clickable {
                        onTareaClick(tarea)
                    }
                )
            }
            item {
                Text(
                    text = searchResultText,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

private fun filterTareas(tareaList: List<Tarea>, query: String): List<Tarea> {
    return tareaList.filter { tarea ->
        tarea.title.contains(query, ignoreCase = true)
    }
}


@Preview
@Composable
fun TareaListScreenPreview() {
    val sampleTareas = listOf(
        Tarea(1, "10-11-2023","Tarea 1", "This is the content of Tarea 1"),
        Tarea(2, "10-11-2023", "Tarea 2", "This is the content of Tarea 2"),
        Tarea(3, "10-11-2023", "Tarea 3", "This is the content of Tarea 3"),
        // Add more sample notes as needed
    )

    TareaListScreen(
        tareaList = sampleTareas,
        onTareaClick = { /* Handle note click */ },
        onAddTareaClick = { /* Handle add note click */ }
    )

}