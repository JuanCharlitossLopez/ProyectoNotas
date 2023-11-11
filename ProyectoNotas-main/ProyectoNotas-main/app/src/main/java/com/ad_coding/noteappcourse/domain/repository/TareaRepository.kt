package com.ad_coding.noteappcourse.domain.repository

import com.ad_coding.noteappcourse.domain.model.Tarea
import kotlinx.coroutines.flow.Flow

interface TareaRepository {
    fun getAllTareas(): Flow<List<Tarea>>

    suspend fun getTareaById(id: Int): Tarea?

    suspend fun insertTarea(tarea: Tarea)

    suspend fun deleteTarea(tarea: Tarea)

    suspend fun updateTarea(tarea: Tarea)
}