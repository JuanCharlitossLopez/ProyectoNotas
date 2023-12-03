package com.example.proyectoNotas_main.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoNotas_main.model.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: Nota)

    @Insert
    suspend fun insertAndGetId(nota: Nota): Long

    @Update
    suspend fun update(nota: Nota)

    @Delete
    suspend fun delete(nota: Nota)

    @Query("SELECT * from notas WHERE id = :id")
    fun getItem(id: Int): Flow<Nota>

    @Query("SELECT * from notas ORDER BY name ASC")
    fun getAllItems(): Flow<List<Nota>>
}
