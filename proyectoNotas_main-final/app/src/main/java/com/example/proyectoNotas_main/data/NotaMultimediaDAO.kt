package com.example.proyectoNotas_main.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoNotas_main.model.NotaMultimedia
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaMultimediaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notaMultimedia: NotaMultimedia)

    @Update
    suspend fun update(notaMultimedia: NotaMultimedia)

    @Delete
    suspend fun delete(notaMultimedia: NotaMultimedia)

    @Query("SELECT * from notaMultimedia WHERE id = :id")
    fun getItem(id: Int): Flow<NotaMultimedia>

    @Query("SELECT * from notaMultimedia WHERE notaId = :notaId")
    fun getAllById(notaId: Int): Flow<List<NotaMultimedia>>

    @Query("SELECT * from notaMultimedia")
    fun getAllItems(): Flow<List<NotaMultimedia>>
}