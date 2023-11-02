package com.example.proyectonotas.datos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyectonotas.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface NotasDAO {
    @Insert
    suspend fun insert(notas: notas)

    @Update
    suspend fun actualizar(notas: notas)

    @Delete
    suspend fun eliminar(notas: notas)

    @Query("SELECT * FROM ${Constants.note_table_name} WHERE id = :id")
    fun getNotas(id: Int): Flow<notas>

    @Query("SELECT * FROM ${Constants.note_table_name} ORDER BY date ASC")
    fun getAllNotas(): Flow<List<notas>>
}