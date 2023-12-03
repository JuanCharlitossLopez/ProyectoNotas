package com.example.proyectoNotas_main.data

import com.example.proyectoNotas_main.model.TareaMultimedia
import kotlinx.coroutines.flow.Flow

class OfflineTareaMultimediaRepository(private val tareaMultimediaDAO: TareaMultimediaDAO) : TareaMultimediaRepository {
    override fun getAllItemsStream(): Flow<List<TareaMultimedia>> = tareaMultimediaDAO.getAllItems()

    override fun getItemStream(id: Int): Flow<TareaMultimedia?> = tareaMultimediaDAO.getItem(id)

    override fun getItemsStreamById(tareaId: Int): Flow<List<TareaMultimedia>> = tareaMultimediaDAO.getAllById(tareaId)

    override suspend fun insertItem(tareaMultimedia: TareaMultimedia) = tareaMultimediaDAO.insert(tareaMultimedia)

    override suspend fun deleteItem(tareaMultimedia: TareaMultimedia) = tareaMultimediaDAO.delete(tareaMultimedia)

    override suspend fun updateItem(tareaMultimedia: TareaMultimedia) = tareaMultimediaDAO.update(tareaMultimedia)
}