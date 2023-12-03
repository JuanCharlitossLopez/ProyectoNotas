package com.example.proyectoNotas_main.viewModel

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoNotas_main.data.TareaMultimediaRepository
import com.example.proyectoNotas_main.data.TareasRepository
import com.example.proyectoNotas_main.ui.TareaEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UpdateTareaViewModel(savedStateHandle: SavedStateHandle,
   private val tareasRepository: TareasRepository,
                           val tareasMultimediaRepository: TareaMultimediaRepository
) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var tareaUiState by mutableStateOf(TareaUiState())
        private set

    var imageUris by mutableStateOf(listOf<Uri>())
    var videoUris by mutableStateOf(listOf<Uri>())

    private val _tareaMultimediaUiState = mutableStateOf(NotaMultimediaUiState())
    val tareaMultimediaUiState: NotaMultimediaUiState
        get() = _tareaMultimediaUiState.value

//    fun setTareaMultimediaUiState(newUiState: TareaMultimediaUiState) {
//        _tareaMultimediaUiState.value = newUiState
//    }

    private val tareaId: Int = checkNotNull(savedStateHandle[TareaEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            tareaUiState = tareasRepository.getItemStream(tareaId)
                .filterNotNull()
                .first()
                .toItemUiState(true)
        }
    }

    /**
     * Update the item in the [TareasRepository]'s data source
     */
    suspend fun updateItem() {
        if (validateInput(tareaUiState.tareaDetails)) {
            tareasRepository.updateItem(tareaUiState.tareaDetails.toItem())
        }
    }

    fun removeUri(uri: Uri) {
        imageUris = imageUris.filter { it != uri }
        videoUris = videoUris.filter { it != uri }
    }

    /**
     * Updates the [notaUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    @SuppressLint("NewApi")
    fun updateUiState(itemDetails: TareaDetails, selectedDate: String) {
        itemDetails.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        itemDetails.fechaACompletar = selectedDate
        val updatedNotaDetails = itemDetails.copy(fecha = itemDetails.fecha, fechaACompletar = itemDetails.fechaACompletar, imageUris = imageUris.joinToString(","), videoUris = videoUris.joinToString(","))
        tareaUiState =
            TareaUiState(tareaDetails = updatedNotaDetails, isEntryValid = validateInput(itemDetails))
    }

    private fun validateInput(uiState: TareaDetails = tareaUiState.tareaDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && fecha.isNotBlank() && contenido.isNotBlank()
        }
    }
}
