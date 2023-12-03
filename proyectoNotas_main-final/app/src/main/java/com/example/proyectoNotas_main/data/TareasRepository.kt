package com.example.proyectoNotas_main.data

import com.example.proyectoNotas_main.model.Tarea
import kotlinx.coroutines.flow.Flow

interface TareasRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<Tarea>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<Tarea?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(tarea: Tarea)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(tarea: Tarea)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(tarea: Tarea)

    suspend fun insertItemAndGetId(tarea: Tarea): Long

}