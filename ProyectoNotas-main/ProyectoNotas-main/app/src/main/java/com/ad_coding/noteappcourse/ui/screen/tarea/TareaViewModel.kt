package com.ad_coding.noteappcourse.ui.screen.tarea

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad_coding.noteappcourse.domain.model.Tarea
import com.ad_coding.noteappcourse.domain.repository.TareaRepository
import com.ad_coding.noteappcourse.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TareaViewModel @Inject constructor(
    private val repository: TareaRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(TareaState())
    val state = _state.asStateFlow()

    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

    init {
        savedStateHandle.get<String>("id")?.let {
            val id = it.toInt()
            viewModelScope.launch {
                repository.getTareaById(id)?.let { tarea ->
                    _state.update { screenState ->
                        screenState.copy(
                            id = tarea.id,
                            date = tarea.date,
                            title = tarea.title,
                            content = tarea.content
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: TareaEvent) {
        when (event) {
            is TareaEvent.ContentChange -> {
                _state.update {
                    it.copy(
                        content = event.value
                    )
                }
            }

            is TareaEvent.TitleChange -> {
                _state.update {
                    it.copy(
                        title = event.value
                    )
                }
            }
            //Actualizar date
            is TareaEvent.DateChange -> {
                _state.update { it.copy(date = event.value) }
            }

            TareaEvent.NavigateBack -> sendEvent(UiEvent.NavigateBack)
            TareaEvent.Save -> {
                viewModelScope.launch {
                    val state = state.value
                    val tarea = Tarea(
                        id = state.id,
                        date = state.date,
                        title = state.title,
                        content = state.content
                    )
                    if (state.id == null) {
                        repository.insertTarea(tarea)
                    } else {
                        repository.updateTarea(tarea)
                    }
                    sendEvent(UiEvent.NavigateBack)
                }
            }

            TareaEvent.DeleteTarea -> {
                viewModelScope.launch {
                    val state = state.value
                    val tarea = Tarea(
                        id = state.id,
                        date = state.date,
                        title = state.title,
                        content = state.content
                    )
                    repository.deleteTarea(tarea)
                }
                sendEvent(UiEvent.NavigateBack)
            }

            else -> {}
        }
    }
}
