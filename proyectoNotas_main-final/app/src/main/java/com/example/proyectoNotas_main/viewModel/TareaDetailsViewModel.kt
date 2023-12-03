package com.example.proyectoNotas_main.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoNotas_main.data.TareasRepository
import com.example.proyectoNotas_main.ui.TareasDetallesDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TareaDetailsViewModel(savedStateHandle: SavedStateHandle,
    private val tareasRepository: TareasRepository,
) : ViewModel() {
    private val tareaId: Int = checkNotNull(savedStateHandle[TareasDetallesDestination.tareaIdArg])

    /**
     * Holds the item details ui state. The data is retrieved from [TareasRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<TareaDetailsUiState> =
        tareasRepository.getItemStream(tareaId)
            .filterNotNull()
            .map {
                TareaDetailsUiState(tareaDetails = it.toItemDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TareaDetailsUiState()
            )

    /**
     * Deletes the item from the [NotasRepository]'s data source.
     */
    suspend fun deleteItem() {
        tareasRepository.deleteItem(uiState.value.tareaDetails.toItem())
    }

    fun markComplete() {
        viewModelScope.launch {
            val currentItem = uiState.value.tareaDetails.toItem()
            tareasRepository.updateItem(currentItem.copy(isComplete = !currentItem.isComplete))
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for TareaDetailsScreen
 */
data class TareaDetailsUiState(
    val complete: Boolean = true,
    val tareaDetails: TareaDetails = TareaDetails()
)