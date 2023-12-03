package com.example.proyectoNotas_main.viewModel

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.proyectoNotas_main.data.TareaMultimediaRepository
import com.example.proyectoNotas_main.data.TareasRepository
import com.example.proyectoNotas_main.model.Tarea
import com.example.proyectoNotas_main.model.TareaMultimedia
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class TareasEditorViewModel(private val tareasRepository: TareasRepository,
                            private val tareasMultimediaRepository: TareaMultimediaRepository
): ViewModel() {

    data class MultimediaDescription(val uri: Uri, var descripcion: String)

    var multimediaDescriptions by mutableStateOf(mutableListOf<MultimediaDescription>())


    //var notaMultimediaUiState by mutableStateOf(NotaMultimediaUiState())

    private val _tareaMultimediaUiState = mutableStateOf(TareaMultimediaUiState())
    val notaMultimediaUiState: TareaMultimediaUiState
        get() = _tareaMultimediaUiState.value

    fun setTareaMultimediaUiState(newUiState: TareaMultimediaUiState) {
        _tareaMultimediaUiState.value = newUiState
    }

    var tareaUiState by mutableStateOf(TareaUiState())
        private set

    var imageUris by mutableStateOf(listOf<Uri>())
    var videoUris by mutableStateOf(listOf<Uri>())

    fun removeUri(uri: Uri) {
        imageUris = imageUris.filter { it != uri }
        videoUris = videoUris.filter { it != uri }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateUiState(tareaDetails: TareaDetails, selectedDate: String) {
        val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val updatedTareaDetails = tareaDetails.copy(fecha = currentDateTime, fechaACompletar = selectedDate, imageUris = imageUris.joinToString(","), videoUris = videoUris.joinToString(","))
        tareaUiState = TareaUiState(tareaDetails = updatedTareaDetails, isEntryValid = validateInput(updatedTareaDetails))
    }

    private fun validateInput(uiState: TareaDetails = tareaUiState.tareaDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && contenido.isNotBlank() && fecha.isNotBlank()
        }
    }

    suspend fun saveTarea() {
        if (validateInput()) {
            val tareaId = tareasRepository.insertItemAndGetId(tareaUiState.tareaDetails.toItem())
            // Guarda los archivos multimedia con el ID de la nota y la descripción del TextField.
            for ((index, uri) in (imageUris).withIndex()) {
                val multimediaDescription = multimediaDescriptions
                    .firstOrNull { it.uri == uri }
                    ?: TareasEditorViewModel.MultimediaDescription(uri, "Sin descripción")

                val tareaMultimedia = TareaMultimedia(
                    uri = uri.toString(),
                    descripcion = multimediaDescription.descripcion,
                    tareaId = tareaId.toInt(),
                    tipo = "imagen"
                )
                tareasMultimediaRepository.insertItem(tareaMultimedia)
            }

            // Guarda los archivos multimedia con el ID de la nota y la descripción del TextField.
            for ((index, uri) in (videoUris).withIndex()) {
                val multimediaDescription = multimediaDescriptions
                    .firstOrNull { it.uri == uri }
                    ?: TareasEditorViewModel.MultimediaDescription(uri, "Sin descripción")

                val tareaMultimedia = TareaMultimedia(
                    uri = uri.toString(),
                    descripcion = multimediaDescription.descripcion,
                    tareaId = tareaId.toInt(),
                    tipo = "video"
                )
                tareasMultimediaRepository.insertItem(tareaMultimedia)
            }
        }
    }
}

data class TareaUiState(
    val tareaDetails: TareaDetails = TareaDetails(),
    val isEntryValid: Boolean = false
)

data class TareaDetails(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    var fecha: String = "",
    var fechaACompletar: String = "",
    val contenido: String = "",
    val isComplite: Boolean = false,
    val imageUris: String = "",
    val videoUris: String = ""
)

fun TareaDetails.toItem(): Tarea = Tarea(
    id = id,
    name = name,
    description = description,
    fecha = fecha,
    fechaACompletar = fechaACompletar,
    contenido = contenido,
    isComplete = isComplite,
    imageUris = imageUris,
    videoUris = videoUris
)

fun Tarea.toItemUiState(isEntryValid: Boolean = false): TareaUiState = TareaUiState(
    tareaDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Tarea.toItemDetails(): TareaDetails = TareaDetails(
    id = id,
    name = name,
    description = description,
    fecha = fecha,
    fechaACompletar = fechaACompletar,
    contenido = contenido,
    isComplite = isComplete,
    imageUris = imageUris,
    videoUris = videoUris
)

//MULTIMEDIA
data class TareaMultimediaUiState(
    val tareaMultimediaDetails: TareaMultimediaDetails = TareaMultimediaDetails(),
    val multimediaDescriptions: MutableMap<String, String> = mutableMapOf()
)
data class TareaMultimediaDetails(
    val id: Int = 0,
    val uri: String = "",
    var descripcion: String = "",
    var tareaId: Int = 0,
    var tipo: String = ""
)


fun TareaMultimediaDetails.toItem(): TareaMultimedia = TareaMultimedia(
    id = id,
    uri = uri,
    descripcion = descripcion,
    tareaId = tareaId,
    tipo = tipo
)

fun TareaMultimedia.toItemUiState(): TareaMultimediaUiState = TareaMultimediaUiState(
    tareaMultimediaDetails = this.toItemDetails()
)

fun TareaMultimedia.toItemDetails(): TareaMultimediaDetails = TareaMultimediaDetails(
    id = id,
    uri = uri,
    descripcion = descripcion,
    tareaId = tareaId,
    tipo = tipo
)
