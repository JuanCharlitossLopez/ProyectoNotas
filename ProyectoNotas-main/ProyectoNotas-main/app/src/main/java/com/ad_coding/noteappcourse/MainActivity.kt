package com.ad_coding.noteappcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ad_coding.noteappcourse.navegacion.Rutas
import com.ad_coding.noteappcourse.ui.screen.note.NoteEvent
import com.ad_coding.noteappcourse.ui.screen.note.NoteScreen
import com.ad_coding.noteappcourse.ui.screen.note.NoteViewModel
//import com.ad_coding.noteappcourse.ui.screen.note.TareasScreen
import com.ad_coding.noteappcourse.ui.screen.note_list.NoteListScreen
import com.ad_coding.noteappcourse.ui.screen.note_list.NoteListViewModel
import com.ad_coding.noteappcourse.ui.screen.tarea_list.TareaListViewModel
import com.ad_coding.noteappcourse.ui.screen.tarea_list.TareaListScreen
import com.ad_coding.noteappcourse.ui.screen.tarea.TareaViewModel
import com.ad_coding.noteappcourse.ui.screen.tarea.TareasScreen
import com.ad_coding.noteappcourse.ui.theme.NoteAppCourseTheme
import com.ad_coding.noteappcourse.ui.util.Route
import com.ad_coding.noteappcourse.ui.util.UiEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppCourseTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.tareaList
                ) {

                    composable(route = Route.noteList) {
                        val viewModel = hiltViewModel<NoteListViewModel>()
                        val noteList by viewModel.noteList.collectAsStateWithLifecycle()

                        NoteListScreen(
                            noteList = noteList,
                            onNoteClick = {
                                navController.navigate(
                                    Route.note.replace(
                                        "{id}",
                                        it.id.toString()
                                    )
                                )
                            },
                            onAddNoteClick = {
                                navController.navigate(Route.note)
                            }
                        )
                    }


                    composable(route = Route.note) {
                        val viewModel = hiltViewModel<NoteViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        LaunchedEffect(key1 = true) {
                            viewModel.event.collect { event ->
                                when (event) {
                                    is UiEvent.NavigateBack -> {
                                        navController.popBackStack()
                                    }
                                    else -> Unit
                                }
                            }
                        }
                        NoteScreen(
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable(route = Route.tareaList) {
                        val viewModel = hiltViewModel<TareaListViewModel>()
                        val tareaList by viewModel.tareaList.collectAsStateWithLifecycle()
                        TareaListScreen(
                            tareaList = tareaList,
                            onTareaClick = {
                                navController.navigate(
                                    Route.tarea.replace(
                                        "{id}",
                                        it.id.toString()
                                    )
                                )
                            },
                            onAddTareaClick = {
                                navController.navigate(Route.tarea)
                            }
                        )
                    }
                    composable(route = Route.tarea) {
                        val viewModel = hiltViewModel<TareaViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = true) {
                            viewModel.event.collect { event ->
                                when (event) {
                                    is UiEvent.NavigateBack -> {
                                        navController.popBackStack()
                                    }
                                    else -> Unit
                                }
                            }
                        }
                        TareasScreen(
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                }

            }
        }
    }
}

