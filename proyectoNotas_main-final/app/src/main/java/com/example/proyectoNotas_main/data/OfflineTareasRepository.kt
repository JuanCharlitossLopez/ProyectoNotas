package com.example.proyectoNotas_main.data

import com.example.proyectoNotas_main.model.Tarea
import kotlinx.coroutines.flow.Flow

class OfflineTareasRepository(private val tareaDao: TareaDAO):TareasRepository {
    override fun getAllItemsStream(): Flow<List<Tarea>> = tareaDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Tarea?> = tareaDao.getItem(id)

    override suspend fun insertItem(tarea: Tarea) = tareaDao.insert(tarea)

    override suspend fun deleteItem(tarea: Tarea) = tareaDao.delete(tarea)

    override suspend fun updateItem(tarea: Tarea) = tareaDao.update(tarea)

    override suspend fun insertItemAndGetId(tarea: Tarea): Long {
        return tareaDao.insertAndGetId(tarea)
    }
}