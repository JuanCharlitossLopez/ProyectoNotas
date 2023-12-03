package com.example.proyectoNotas_main.data

import com.example.proyectoNotas_main.model.Nota
import kotlinx.coroutines.flow.Flow

class NotasRepositoryImpl : NotasRepository {
    override fun getAllItemsStream(): Flow<List<Nota>> {
        // Implementa este método para devolver un flujo de todas las notas
        return TODO("Provide the return value")
    }

    override fun getItemStream(id: Int): Flow<Nota?> {
        // Implementa este método para devolver un flujo de una nota específica basada en su id
        return TODO("Provide the return value")
    }

    override suspend fun insertItem(nota: Nota) {
        // Implementa este método para insertar una nota
    }

    override suspend fun insertItemAndGetId(nota: Nota): Long {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(nota: Nota) {
        // Implementa este método para eliminar una nota
    }

    override suspend fun updateItem(nota: Nota) {
        // Implementa este método para actualizar una nota
    }
}