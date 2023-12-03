package com.example.proyectoNotas_main.data

import com.example.proyectoNotas_main.model.NotaMultimedia
import kotlinx.coroutines.flow.Flow

interface NotaMultimediaRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<NotaMultimedia>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<NotaMultimedia?>

    /**
     * Retrieve all the items from the given data source that matches with the [notaId].
     */
    fun getItemsStreamById(notaId: Int): Flow<List<NotaMultimedia>>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(notaMultimedia: NotaMultimedia)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(notaMultimedia: NotaMultimedia)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(notaMultimedia: NotaMultimedia)


    //suspend fun getAllById(idNota: Int)
}