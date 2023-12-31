@file:OptIn(ExperimentalMaterial3Api::class)

package com.ad_coding.noteappcourse.ui.screen.note_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
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
import com.ad_coding.noteappcourse.domain.model.Note
//import com.ad_coding.noteappcourse.ui.screen.note.TareasScreen
import com.ad_coding.noteappcourse.window.infoWindow
import com.ad_coding.noteappcourse.window.rememberWindow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    noteList: List<Note>,
    onNoteClick: (Note) -> Unit,
    onAddNoteClick: () -> Unit
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    var searchText by remember { mutableStateOf(TextFieldValue()) }
    var filteredNotes by remember { mutableStateOf(noteList) }
    var searchResultText by remember { mutableStateOf("") }

    // Inicializar filteredNotes con todas las notas al principio
    filteredNotes = noteList
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
                            filteredNotes = filterNotes(noteList, it.text)
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
                            filteredNotes = filterNotes(noteList, searchText.text)
                            if (filteredNotes.isNotEmpty()) {
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
                onClick = onAddNoteClick
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Agregar nota"
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

            item {
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.titleLarge

                )
            }
            items(filteredNotes) { note ->
                ListItem(
                    headlineText = {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    supportingText = {
                        Text(
                            text = note.content,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    modifier = Modifier.clickable {
                        onNoteClick(note)
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

private fun filterNotes(noteList: List<Note>, query: String): List<Note> {
    return noteList.filter { note ->
        note.title.contains(query, ignoreCase = true)
    }
}


@Preview
@Composable
fun NoteListScreenPreview() {
    val sampleNotes = listOf(
        Note(1, "Note 1", "This is the content of Note 1"),
        Note(2, "Note 2", "This is the content of Note 2"),
        // Add more sample notes as needed
    )


    NoteListScreen(
        noteList = sampleNotes,
        onNoteClick = {  },
        onAddNoteClick = { /* Handle add note click */ }
    )

}