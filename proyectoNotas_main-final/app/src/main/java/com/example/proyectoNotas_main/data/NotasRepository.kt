package com.example.proyectoNotas_main.data

import com.example.proyectoNotas_main.model.Nota
import kotlinx.coroutines.flow.Flow

interface NotasRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<Nota>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<Nota?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(nota: Nota)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(nota: Nota)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(nota: Nota)

    suspend fun insertItemAndGetId(nota: Nota): Long

}