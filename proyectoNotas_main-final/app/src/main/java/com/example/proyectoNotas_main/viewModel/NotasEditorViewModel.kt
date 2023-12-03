package com.example.proyectoNotas_main.viewModel

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.proyectoNotas_main.data.NotaMultimediaRepository
import com.example.proyectoNotas_main.data.NotasRepository
import com.example.proyectoNotas_main.model.Nota
import com.example.proyectoNotas_main.model.NotaMultimedia
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class NotasEditorViewModel(private val notasRepository: NotasRepository,
                           private val notasMultimediaRepository: NotaMultimediaRepository
) : ViewModel() {

    data class MultimediaDescription(val uri: Uri, var descripcion: String)

    var multimediaDescriptions by mutableStateOf(mutableListOf<MultimediaDescription>())
    var notaUiState by mutableStateOf(NotaUiState())
        private set

    //var notaMultimediaUiState by mutableStateOf(NotaMultimediaUiState())

    private val _notaMultimediaUiState = mutableStateOf(NotaMultimediaUiState())
    val notaMultimediaUiState: NotaMultimediaUiState
        get() = _notaMultimediaUiState.value

    fun setNotaMultimediaUiState(newUiState: NotaMultimediaUiState) {
        _notaMultimediaUiState.value = newUiState
    }


    var imageUris by mutableStateOf(listOf<Uri>())
    var videoUris by mutableStateOf(listOf<Uri>())
    var audioUris by mutableStateOf(listOf<Uri>())


    @RequiresApi(Build.VERSION_CODES.O)
    fun updateUiState(notaDetails: NotaDetails) {
        val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val updatedNotaDetails = notaDetails.copy(fecha = currentDateTime, imageUris = imageUris.joinToString(","),
            videoUris = videoUris.joinToString(","))
        notaUiState = NotaUiState(notaDetails = updatedNotaDetails, isEntryValid = validateInput(updatedNotaDetails))
    }

    fun removeUri(uri: Uri) {
        imageUris = imageUris.filter { it != uri }
        videoUris = videoUris.filter { it != uri }
    }

    private fun validateInput(uiState: NotaDetails = notaUiState.notaDetails): Boolean {
        return with(uiState) {
            name.isNotBlank()  && contenido.isNotBlank() && fecha.isNotBlank()
        }
    }


    suspend fun saveNota() {
        if (validateInput()) {
            // Guarda la nota y obtén el ID.
            val notaId = notasRepository.insertItemAndGetId(notaUiState.notaDetails.toItem())

            // Guarda los archivos multimedia con el ID de la nota y la descripción del TextField.
            for ((index, uri) in (imageUris).withIndex()) {
                val multimediaDescription = multimediaDescriptions
                    .firstOrNull { it.uri == uri }
                    ?: MultimediaDescription(uri, "Sin descripción")

                val notaMultimedia = NotaMultimedia(
                    uri = uri.toString(),
                    descripcion = multimediaDescription.descripcion,
                    notaId = notaId.toInt(),
                    tipo = "imagen"
                )
                notasMultimediaRepository.insertItem(notaMultimedia)
            }

            // Guarda los archivos multimedia con el ID de la nota y la descripción del TextField.
            for ((index, uri) in (videoUris).withIndex()) {
                val multimediaDescription = multimediaDescriptions
                    .firstOrNull { it.uri == uri }
                    ?: MultimediaDescription(uri, "Sin descripción")

                val notaMultimedia = NotaMultimedia(
                    uri = uri.toString(),
                    descripcion = multimediaDescription.descripcion,
                    notaId = notaId.toInt(),
                    tipo = "video"
                )
                notasMultimediaRepository.insertItem(notaMultimedia)
            }

            for (uri in audioUris) {
                val multimediaDescription = multimediaDescriptions
                    .firstOrNull { it.uri == uri }
                    ?: MultimediaDescription(uri, "Sin descripción")

                val notaMultimedia = NotaMultimedia(
                    uri = uri.toString(),
                    descripcion = multimediaDescription.descripcion,
                    notaId = notaId.toInt(),
                    tipo = "audio"
                )
                notasMultimediaRepository.insertItem(notaMultimedia)
            }
        }
    }
}


data class NotaUiState(
    val notaDetails: NotaDetails = NotaDetails(),
    val isEntryValid: Boolean = false
)

data class NotaDetails(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    var fecha: String = "",
    val contenido: String = "",
    val imageUris: String = "",
    val videoUris: String = ""
)

fun NotaDetails.toItem(): Nota = Nota(
    id = id,
    name = name,
    description = description,
    fecha = fecha,
    contenido = contenido,
    imageUris = imageUris,
    videoUris = videoUris
)

fun Nota.toItemUiState(isEntryValid: Boolean = false): NotaUiState = NotaUiState(
    notaDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Nota.toItemDetails(): NotaDetails = NotaDetails(
    id = id,
    name = name,
    description = description,
    fecha = fecha,
    contenido = contenido,
    imageUris = imageUris,
    videoUris = videoUris
)

//MULTIMEDIA
data class NotaMultimediaUiState(
    val notaMultimediaDetails: NotaMultimediaDetails = NotaMultimediaDetails(),
    val multimediaDescriptions: MutableMap<String, String> = mutableMapOf()
)
data class NotaMultimediaDetails(
    val id: Int = 0,
    val uri: String = "",
    var descripcion: String = "",
    var notaId: Int = 0,
    var tipo: String = ""
)

fun NotaMultimediaDetails.toItem(): NotaMultimedia = NotaMultimedia(
    id = id,
    uri = uri,
    descripcion = descripcion,
    notaId = notaId,
    tipo = tipo
)

fun NotaMultimedia.toItemUiState(): NotaMultimediaUiState = NotaMultimediaUiState(
    notaMultimediaDetails = this.toItemDetails()
)

fun NotaMultimedia.toItemDetails(): NotaMultimediaDetails = NotaMultimediaDetails(
    id = id,
    uri = uri,
    descripcion = descripcion,
    notaId = notaId,
    tipo = tipo
)
