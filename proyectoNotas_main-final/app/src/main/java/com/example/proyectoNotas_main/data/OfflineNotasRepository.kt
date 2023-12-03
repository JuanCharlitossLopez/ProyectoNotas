package com.example.proyectoNotas_main.data

import com.example.proyectoNotas_main.model.Nota
import kotlinx.coroutines.flow.Flow

class OfflineNotasRepository(private val notaDAO: NotaDAO) : NotasRepository{
    override fun getAllItemsStream(): Flow<List<Nota>> = notaDAO.getAllItems()

    override fun getItemStream(id: Int): Flow<Nota?> = notaDAO.getItem(id)

    override suspend fun insertItem(nota: Nota) = notaDAO.insert(nota)

    override suspend fun deleteItem(nota: Nota) = notaDAO.delete(nota)

    override suspend fun updateItem(nota: Nota) = notaDAO.update(nota)

    override suspend fun insertItemAndGetId(nota: Nota): Long {
        return notaDAO.insertAndGetId(nota)
    }

}