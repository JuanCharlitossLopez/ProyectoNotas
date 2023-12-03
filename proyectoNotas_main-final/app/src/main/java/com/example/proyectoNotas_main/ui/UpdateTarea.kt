package com.example.proyectoNotas_main.ui

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.proyectoNotas_main.AppViewModelProvider
import com.example.proyectoNotas_main.ComposeFileProvider
import com.example.proyectoNotas_main.InventoryTopAppBar
import com.example.proyectoNotas_main.NavigationDestination2
import com.example.proyectoNotas_main.R
import com.example.proyectoNotas_main.VideoPlayer
import com.example.proyectoNotas_main.viewModel.UpdateTareaViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime

object TareaEditDestination : NavigationDestination2 {
    override val route = "tarea_edit"
    override val titleRes = R.string.edit_item_title2
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTareaScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateTareaViewModel = viewModel(factory = AppViewModelProvider.Factory),
    alarmScheduler: AlarmScheduler
) {
    val coroutineScope = rememberCoroutineScope()
    var selectedDate by rememberSaveable { mutableStateOf("") }
    var selectedTime by rememberSaveable { mutableStateOf("") }
    var isDateSelected by rememberSaveable { mutableStateOf(false) }
    var isTimeSelected by rememberSaveable { mutableStateOf(false) }

    var imageUris by remember { mutableStateOf(listOf<Uri>()) }
    var videoUris by remember { mutableStateOf(listOf<Uri>()) }

    //MULTIMEDIA
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var videoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                imageUris = imageUris.plus(uri!!)
                viewModel.imageUris=viewModel.imageUris.plus(uri!!)
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success && imageUri != null) {
                imageUris = imageUris.plus(imageUri!!)
                viewModel.imageUris=viewModel.imageUris.plus(imageUri!!)
            }
        }
    )

    val videoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo(),
        onResult = { success ->
            if (success && videoUri != null) {
                videoUris = videoUris.plus(videoUri!!)
                viewModel.videoUris=viewModel.videoUris.plus(videoUri!!)
            }
        }
    )

    val context = LocalContext.current
    //MULTIMEDIA

    //ALARMA
    var secondText by remember {
        mutableStateOf("")
    }
    var messageText by remember {
        mutableStateOf("")
    }
    var alarmItem : AlarmItem? = null
    //ALARMA

    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(TareaEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(55.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                TimePicker(onTimeSelected = {
                    selectedTime = it
                    isTimeSelected = true
                }, isEnabled = isDateSelected)
                Spacer(modifier = Modifier.width(10.dp))
                DatePicker(onDateSelected = {
                    selectedDate = it
                    isDateSelected=true
                })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.recordatorio),fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    /*alarmItem =
                       AlarmItem(
                           alarmTime = LocalDateTime.now().plusSeconds(
                               secondText.toLong()
                           ),
                           message = messageText
                       )*/
                    alarmItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        AlarmItem(
                            LocalDateTime.now().plusSeconds(1000),
                            "",
                            selectedDate,
                            selectedTime
                        )
                    } else {
                        TODO("VERSION.SDK_INT < O")
                    }
                    alarmItem?.let(alarmScheduler::schedule)
                    secondText = ""
                    messageText = ""

                }, enabled = isTimeSelected) {
                    Text(text = stringResource(R.string.reminder))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    alarmItem?.let(alarmScheduler::cancel)
                }, enabled = isTimeSelected) {
                    Text(text = stringResource(R.string.cancelar))
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            TareaEntryBody(
                tareaUiState = viewModel.tareaUiState,
                onTareaValueChange = { updatedTareaDetails ->
                    val combinedDateTime = "$selectedDate $selectedTime"
                    viewModel.updateUiState(updatedTareaDetails, combinedDateTime)
                },
                onSaveClick = {
                    // Note: If the user rotates the screen very fast, the operation may get cancelled
                    // and the item may not be updated in the Database. This is because when config
                    // change occurs, the Activity will be recreated and the rememberCoroutineScope will
                    // be cancelled - since the scope is bound to composition.
                    coroutineScope.launch {
                        val combinedDateTime = "$selectedDate $selectedTime"
                        viewModel.updateUiState(viewModel.tareaUiState.tareaDetails, combinedDateTime)
                        viewModel.updateItem()
                        navigateBack()
                    }
                },
                modifier = Modifier.padding(innerPadding)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        val uri = ComposeFileProvider.getImageUri(context)
                        imageUri = uri
                        cameraLauncher.launch(uri)
                    }) {
                    Image(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(4.dp),
                        painter = painterResource(R.drawable.camara_fotografica),
                        contentDescription = null
                    )
                }
                Button(
                    onClick = {
                        val uri = ComposeFileProvider.getVideoUri(context)
                        videoUri = uri
                        videoLauncher.launch(uri)
                    } ) {
                    Image(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(4.dp),
                        painter = painterResource(R.drawable.video),
                        contentDescription = null
                    )
                }
                Button(onClick = { imagePicker.launch("image/*") }) {
                    Image(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(4.dp),
                        painter = painterResource(R.drawable.carpeta),
                        contentDescription = null
                    )
                }
                Button(onClick = { /*TODO*/ }) {
                    Image(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(4.dp),
                        painter = painterResource(R.drawable.microfono),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))        //MOSTRAR MULTIMEDIA
            val imagenesCargadas = viewModel.tareaUiState.tareaDetails.imageUris.split(",")
            val videosCargados = viewModel.tareaUiState.tareaDetails.videoUris.split(",")
            val nuevos = imageUris + videoUris

            val combinedList = listOf(imagenesCargadas, videosCargados, nuevos)

            LazyColumn {
                itemsIndexed(combinedList) { index, list ->
                    when (index) {
                        0 -> { // Renderiza las imágenes cargadas aquí
                            list.forEach { uri ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Column {
                                        val parsedUri = Uri.parse(uri.toString())
                                        AsyncImage(
                                            model = parsedUri,
                                            modifier = Modifier
                                                .height(400.dp)
                                                .fillMaxWidth()
                                                .align(Alignment.CenterHorizontally),
                                            contentDescription = "Selected image",
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        // Agrega el botón aquí
                                        Button(
                                            onClick = {
                                                // Elimina la tarjeta y quita la imagen del arreglo.
                                                imageUris = imageUris.filter { it != uri }
                                                videoUris = videoUris.filter { it != uri }
                                                val u = Uri.parse(uri.toString())
                                                viewModel.removeUri(u)
                                            },
                                            modifier = Modifier.align(Alignment.End)
                                        ) {
                                            Text("Eliminar")
                                        }
                                    }
                                }
                            }
                        }
                        1 -> { // Renderiza los videos cargados aquí
                            list.forEach { uri ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Column {
                                        val parsedUri = Uri.parse(uri.toString())
                                        VideoPlayer(
                                            videoUri = parsedUri,
                                            modifier = Modifier
                                                .height(400.dp)
                                                .fillMaxWidth()
                                                .align(Alignment.CenterHorizontally)
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        // Agrega el botón aquí
                                        Button(
                                            onClick = {
                                                // Elimina la tarjeta y quita la imagen del arreglo.
                                                imageUris = imageUris.filter { it != uri }
                                                videoUris = videoUris.filter { it != uri }
                                                val u = Uri.parse(uri.toString())
                                                viewModel.removeUri(u)
                                            },
                                            modifier = Modifier.align(Alignment.End)
                                        ) {
                                            Text("Eliminar")
                                        }
                                    }
                                }
                            }
                        }
                        2 -> { // Renderiza los nuevos elementos aquí
                            Text(text = "Nuevos elementos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(16.dp))
                            list.forEach { uri ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Column {
                                        if (uri in imageUris) {
                                            AsyncImage(
                                                model = uri,
                                                modifier = Modifier
                                                    .height(400.dp)
                                                    .fillMaxWidth()
                                                    .align(CenterHorizontally),
                                                contentDescription = "Selected image",
                                            )
                                        } else if (uri in videoUris) {
                                            val parsedUri = Uri.parse(uri.toString())
                                            VideoPlayer(
                                                videoUri = parsedUri,
                                                modifier = Modifier
                                                    .height(400.dp)
                                                    .fillMaxWidth()
                                                    .align(CenterHorizontally)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(16.dp))
                                        // Agrega el TextField para la descripción aquí
//                                        TextField(
//                                            value = viewModel.notaMultimediaUiState.notaMultimediaDetails.descripcion,
//                                            onValueChange = { newDescription ->
//                                                viewModel.setNotaMultimediaUiState(
//                                                    viewModel.notaMultimediaUiState.copy(
//                                                        notaMultimediaDetails = viewModel.notaMultimediaUiState.notaMultimediaDetails.copy(descripcion = newDescription)
//                                                    )
//                                                )
//                                            },
//                                            label = { Text("Descripción") },
//                                            modifier = Modifier.fillMaxWidth(),
//                                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
//                                        )
//                                        Spacer(modifier = Modifier.height(16.dp))
                                        // Agrega el botón aquí
                                        Button(
                                            onClick = {
                                                // Elimina la tarjeta y quita la imagen del arreglo.
                                                imageUris = imageUris.filter { it != uri }
                                                videoUris = videoUris.filter { it != uri }
                                                val u = Uri.parse(uri.toString())
                                                viewModel.removeUri(u)
                                            },
                                            modifier = Modifier.align(Alignment.End)
                                        ) {
                                            Text(stringResource(R.string.delete))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}


