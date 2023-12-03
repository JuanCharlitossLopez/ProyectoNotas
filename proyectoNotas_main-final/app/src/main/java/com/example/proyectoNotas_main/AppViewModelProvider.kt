package com.example.proyectoNotas_main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.proyectoNotas_main.viewModel.NotaDetailsViewModel
import com.example.proyectoNotas_main.viewModel.NotasEditorViewModel
import com.example.proyectoNotas_main.viewModel.NotasScreenViewModel
import com.example.proyectoNotas_main.viewModel.TareaDetailsViewModel
import com.example.proyectoNotas_main.viewModel.TareasEditorViewModel
import com.example.proyectoNotas_main.viewModel.TareasScreenViewModel
import com.example.proyectoNotas_main.viewModel.UpdateNotaViewModel
import com.example.proyectoNotas_main.viewModel.UpdateTareaViewModel

object AppViewModelProvider{
    @RequiresApi(Build.VERSION_CODES.O)
    val Factory = viewModelFactory {
        initializer {
            NotasEditorViewModel(notasApplication().container.notasRepository,
                notasApplication().container.notasMultimediaRepository
            )
        }

        initializer {
            TareasEditorViewModel(notasApplication().container.tareasRepository,
                notasApplication().container.tareasMultimediaRepository
            )
        }

        initializer {
            NotasScreenViewModel(notasApplication().container.notasRepository)
        }

        initializer {
            TareasScreenViewModel(notasApplication().container.tareasRepository)
        }

        initializer {
            NotaDetailsViewModel(
                this.createSavedStateHandle(),
                notasApplication().container.notasRepository
            )
        }

        initializer {
            UpdateNotaViewModel(
                this.createSavedStateHandle(),
                notasApplication().container.notasRepository,
                notasApplication().container.notasMultimediaRepository
            )
        }

        initializer {
            TareaDetailsViewModel(
                this.createSavedStateHandle(),
                notasApplication().container.tareasRepository
            )
        }

        initializer {
            UpdateTareaViewModel(
                this.createSavedStateHandle(),
                notasApplication().container.tareasRepository,
                notasApplication().container.tareasMultimediaRepository
            )
        }
    }
}

fun CreationExtras.notasApplication(): NotasApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as NotasApplication)