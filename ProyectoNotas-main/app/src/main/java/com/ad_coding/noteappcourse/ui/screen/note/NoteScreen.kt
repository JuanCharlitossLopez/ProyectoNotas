@file:OptIn(ExperimentalMaterial3Api::class)

package com.ad_coding.noteappcourse.ui.screen.note

import android.icu.util.Calendar
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Panorama
import androidx.compose.material.icons.rounded.VideoCall
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ad_coding.noteappcourse.ComposeFileProvider
import com.ad_coding.noteappcourse.window.infoWindow
import com.ad_coding.noteappcourse.window.rememberWindow
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit
) {
    // 1
    var hasImage by remember {
        mutableStateOf(false)
    }
    var hasVideo by remember {
        mutableStateOf(false)
    }
    // 2
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            // TODO
            // 3
            hasImage = uri != null
            imageUri = uri
        }
    )
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
        }
    )
    val videoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo(),
        onResult = { success ->
            hasVideo = success
        }
    )
    val windowInfo = rememberWindow()//Para detectar el tipo de pantalla
    val context = LocalContext.current
    if(windowInfo.windowHeightInfo is infoWindow.tipoPantalla.Compact){
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { /*TODO*/ },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onEvent(NoteEvent.NavigateBack)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "navigate back",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                onEvent(NoteEvent.DeleteNote)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "delete",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                imagePicker.launch("image/*")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Panorama,
                                contentDescription = "galery",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                val uri = ComposeFileProvider.getImageUri(context)
                                imageUri = uri
                                cameraLauncher.launch(uri)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.CameraAlt,
                                contentDescription = "photo",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                val uri = ComposeFileProvider.getImageUri(context)
                                imageUri = uri
                                videoLauncher.launch(uri)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.VideoCall,
                                contentDescription = "video",
                                modifier = Modifier.size(30.dp)
                            )
                        }

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
                        onEvent(NoteEvent.TitleChange(it))
                    },
                    placeholder = {
                        Text(text = "Titulo")
                    },
                    modifier = Modifier
                        .fillMaxWidth()


                )
                OutlinedTextField(
                    value = state.content,
                    onValueChange = {
                        onEvent(NoteEvent.ContentChange(it))
                    },
                    placeholder = {
                        Text(text = "Contenido")
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
                            onEvent(NoteEvent.Save)
                        },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Text(text = "Guardar")
                    }
                    // 4
                    if ((hasImage or hasVideo) && imageUri != null) {
                        // 5
                        if(hasImage){
                            AsyncImage(
                                model = imageUri,
                                modifier = Modifier.fillMaxWidth(),
                                contentDescription = "Selected image",
                            )
                        }
                        if(hasVideo) {VideoPlayer(videoUri = imageUri!!)}
                    }
                }
            }
        }
    }
    //PANTALLA MEDIANA
    if(windowInfo.windowHeightInfo is infoWindow.tipoPantalla.Medium){
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { /*TODO*/ },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onEvent(NoteEvent.NavigateBack)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "navigate back",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                onEvent(NoteEvent.DeleteNote)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "delete",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                imagePicker.launch("image/*")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Panorama,
                                contentDescription = "galery",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                val uri = ComposeFileProvider.getImageUri(context)
                                imageUri = uri
                                cameraLauncher.launch(uri)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.CameraAlt,
                                contentDescription = "photo",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                val uri = ComposeFileProvider.getImageUri(context)
                                imageUri = uri
                                videoLauncher.launch(uri)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.VideoCall,
                                contentDescription = "video",
                                modifier = Modifier.size(30.dp)
                            )
                        }

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
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedTextField(
                    value = state.title,
                    onValueChange = {
                        onEvent(NoteEvent.TitleChange(it))
                    },
                    placeholder = {
                        Text(text = "Titulo")
                    },
                    modifier = Modifier
                        .fillMaxWidth()


                )
                OutlinedTextField(
                    value = state.content,
                    onValueChange = {
                        onEvent(NoteEvent.ContentChange(it))
                    },
                    placeholder = {
                        Text(text = "Contenido")
                    },
                    modifier = Modifier
                        .fillMaxWidth()

                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                )
                {
                    Button(
                        onClick = {
                            onEvent(NoteEvent.Save)
                        },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Text(text = "Guardar")
                    }
                }
                // 4
                if ((hasImage or hasVideo) && imageUri != null) {
                    // 5
                    if(hasImage){
                        AsyncImage(
                            model = imageUri,
                            modifier = Modifier.fillMaxWidth(),
                            contentDescription = "Selected image",
                        )
                    }
                    if(hasVideo) {VideoPlayer(videoUri = imageUri!!)}
                }
            }
        }
    }
    //PANTALLA GRANDE
    if(windowInfo.windowHeightInfo is infoWindow.tipoPantalla.Expanded){
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { /*TODO*/ },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onEvent(NoteEvent.NavigateBack)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "navigate back",
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                onEvent(NoteEvent.DeleteNote)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "delete",
                                modifier = Modifier.size(48.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                imagePicker.launch("image/*")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Panorama,
                                contentDescription = "galery",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                val uri = ComposeFileProvider.getImageUri(context)
                                imageUri = uri
                                cameraLauncher.launch(uri)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.CameraAlt,
                                contentDescription = "photo",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                val uri = ComposeFileProvider.getImageUri(context)
                                imageUri = uri
                                videoLauncher.launch(uri)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.VideoCall,
                                contentDescription = "video",
                                modifier = Modifier.size(30.dp)
                            )
                        }

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
                        onEvent(NoteEvent.TitleChange(it))
                    },
                    placeholder = {
                        Text(text = "Titulo PANTALLA GRANDE")
                    },
                    modifier = Modifier
                        .fillMaxWidth()


                )
                OutlinedTextField(
                    value = state.content,
                    onValueChange = {
                        onEvent(NoteEvent.ContentChange(it))
                    },
                    placeholder = {
                        Text(text = "Contenido")
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
                            onEvent(NoteEvent.Save)
                        },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Text(text = "Guardar GRANDE GUARDAR")
                    }
                }
                // 4
                if ((hasImage or hasVideo) && imageUri != null) {
                    // 5
                    if(hasImage){
                        AsyncImage(
                            model = imageUri,
                            modifier = Modifier.fillMaxWidth(),
                            contentDescription = "Selected image",
                        )
                    }
                    if(hasVideo) {VideoPlayer(videoUri = imageUri!!)}
                }
            }
        }
    }

}

@Composable
fun VideoPlayer(videoUri: Uri, modifier: Modifier = Modifier.fillMaxWidth()) {
    val context = LocalContext.current
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
        }
    }
    val playbackState = exoPlayer
    val isPlaying = playbackState?.isPlaying ?: false

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        },
        modifier = modifier
    )

    IconButton(
        onClick = {
            if (isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.play()
            }
        },
        modifier = Modifier
            //.align(Alignment.BottomEnd)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = if (isPlaying) Icons.Filled.Refresh else Icons.Filled.PlayArrow,
            contentDescription = if (isPlaying) "Pause" else "Play",
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
    }
}

/*
//Segunda ventana de Tareas
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareasScreen(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit
) {
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
                            onEvent(NoteEvent.NavigateBack)
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
                            onEvent(NoteEvent.DeleteNote)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "delete"
                        )
                    }
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
                    onEvent(NoteEvent.TitleChange(it))
                },
                placeholder = {
                    Text(text = "Title")
                },
                modifier = Modifier
                    .fillMaxWidth()

            )
            OutlinedTextField(
                value = state.content,
                onValueChange = {
                    onEvent(NoteEvent.ContentChange(it))
                },
                placeholder = {
                    Text(text = "Content")
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
                        onEvent(NoteEvent.Save)
                    },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}
*/

@Preview
@Composable
fun previewNoteScreen() {
    val exampleNoteState = NoteState(
        title = "Título",
        content = "Contenido"
    )

    NoteScreen(
        state = exampleNoteState,
        onEvent = {
            // Define un manejador de eventos de ejemplo aquí
        }
    )
}

