package com.example.proyectoNotas_main.data

import com.example.proyectoNotas_main.model.NotaMultimedia
import kotlinx.coroutines.flow.Flow

class OfflineNotaMultimediaRepository(private val notaMultimediaDAO: NotaMultimediaDAO) : NotaMultimediaRepository {

    override fun getAllItemsStream(): Flow<List<NotaMultimedia>> = notaMultimediaDAO.getAllItems()

    override fun getItemStream(id: Int): Flow<NotaMultimedia?> = notaMultimediaDAO.getItem(id)

    override fun getItemsStreamById(notId: Int): Flow<List<NotaMultimedia>> = notaMultimediaDAO.getAllById(notId)

    override suspend fun insertItem(notaMultimedia: NotaMultimedia) = notaMultimediaDAO.insert(notaMultimedia)

    override suspend fun deleteItem(notaMultimedia: NotaMultimedia) = notaMultimediaDAO.delete(notaMultimedia)

    override suspend fun updateItem(notaMultimedia: NotaMultimedia) = notaMultimediaDAO.update(notaMultimedia)

}