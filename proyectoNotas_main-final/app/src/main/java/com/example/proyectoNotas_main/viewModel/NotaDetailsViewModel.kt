package com.example.proyectoNotas_main.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoNotas_main.data.NotasRepository
import com.example.proyectoNotas_main.ui.NotasDetallesDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NotaDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val notasRepository: NotasRepository,
) : ViewModel() {

    private val notaId: Int = checkNotNull(savedStateHandle[NotasDetallesDestination.notaIdArg])

    /**
     * Holds the item details ui state. The data is retrieved from [ItemsRepository] and mapped to
     * the UI state.
     */
    val uiState: StateFlow<NotaDetailsUiState> =
        notasRepository.getItemStream(notaId)
            .filterNotNull()
            .map {
                NotaDetailsUiState(notaDetails = it.toItemDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = NotaDetailsUiState()
            )

    /**
     * Deletes the item from the [NotasRepository]'s data source.
     */
    suspend fun deleteItem() {
        notasRepository.deleteItem(uiState.value.notaDetails.toItem())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for NotaDetailsScreen
 */
data class NotaDetailsUiState(
    val notaDetails: NotaDetails = NotaDetails()
)
