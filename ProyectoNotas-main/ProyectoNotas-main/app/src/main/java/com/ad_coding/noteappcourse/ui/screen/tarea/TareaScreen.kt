package com.ad_coding.noteappcourse.ui.screen.tarea

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ad_coding.noteappcourse.ui.screen.tarea.TareaEvent
import com.ad_coding.noteappcourse.ui.screen.tarea.TareaState
import com.ad_coding.noteappcourse.window.infoWindow
import com.ad_coding.noteappcourse.window.rememberWindow
import java.util.Calendar

//Segunda ventana de Tareas
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareasScreen(
    state: TareaState,
    onEvent: (TareaEvent) -> Unit
) {
    //Meter la pantalla a diferentes sizes
    val windowInfo = rememberWindow()//Para detectar el tipo de pantalla
    if(windowInfo.windowHeightInfo is infoWindow.tipoPantalla.Compact){

    }

    if(windowInfo.windowHeightInfo is infoWindow.tipoPantalla.Medium){

    }

    if(windowInfo.windowHeightInfo is infoWindow.tipoPantalla.Expanded){

    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(TareaEvent.NavigateBack)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(TareaEvent.DeleteTarea)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "delete"
                        )
                    }/*
                    IconButton(
                        onClick = {
                            onEvent(TareaEvent.DateChange("DATE"))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.DateRange,
                            contentDescription = "delete"
                        )
                    }*/
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(
                    horizontal = 20.dp,
                    vertical = 15.dp

                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = {
                    onEvent(TareaEvent.TitleChange(it))
                },
                placeholder = {
                    Text(text = "Title de tarea")
                },
                modifier = Modifier
                    .fillMaxWidth()

            )
            OutlinedTextField(
                value = state.date,
                onValueChange = {
                    onEvent(TareaEvent.DateChange(it))
                },
                placeholder = {
                    Text(text = "DD-MM-AAAA")
                },
                modifier = Modifier
                    .fillMaxWidth()

            )
            OutlinedTextField(
                value = state.content,
                onValueChange = {
                    onEvent(TareaEvent.ContentChange(it))
                },
                placeholder = {
                    Text(text = "Content de tarea")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        onEvent(TareaEvent.Save)
                    },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }

}
@Preview
@Composable
fun previewTareaScreen() {
    val exampleNoteState = TareaState(
        title = "Título",
        date = "10-11-2023",
        content = "Contenido"
    )

    TareasScreen(
        state = exampleNoteState,
        onEvent = {
            // Define un manejador de eventos de ejemplo aquí
        }
    )
}


/*
@Composable
fun TimePicker() {
    val mCalendar: Calendar = Calendar.getInstance()
    val hour = mCalendar[Calendar.HOUR_OF_DAY]
    val minute = mCalendar[Calendar.MINUTE]

    val time = remember { mutableStateOf("") }
    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hour: Int, minute: Int ->
            time.value = "$hour:$minute"
        }, hour, minute, true
    )

    Icon(
        imageVector = Icons.Filled.Notifications,
        contentDescription = "Recordatorios",
        modifier = Modifier
            .clickable {
                timePickerDialog.show()
            }
        )
}*/
