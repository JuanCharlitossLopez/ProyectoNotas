package com.ad_coding.noteappcourse.data.repository

import com.ad_coding.noteappcourse.data.local.dao.TareaDao
import com.ad_coding.noteappcourse.data.mapper.asExternalModel
import com.ad_coding.noteappcourse.data.mapper.toEntity
import com.ad_coding.noteappcourse.domain.model.Tarea
import com.ad_coding.noteappcourse.domain.repository.TareaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TareaRepositoryImpl(private val dao: TareaDao) : TareaRepository {
    override fun getAllTareas(): Flow<List<Tarea>> {
        return dao.getAllTareas()
            .map { tareas ->
                tareas.map {
                    it.asExternalModel()
                }
            }
    }

    override suspend fun getTareaById(id: Int): Tarea? {
        return dao.getTareaById(id)?.asExternalModel()
    }

    override suspend fun insertTarea(tarea: Tarea) {
        dao.insertTarea(tarea.toEntity())
    }

    override suspend fun deleteTarea(tarea: Tarea) {
        dao.deleteTarea(tarea.toEntity())
    }

    override suspend fun updateTarea(tarea: Tarea) {
        dao.updateTarea(tarea.toEntity())
    }
}
