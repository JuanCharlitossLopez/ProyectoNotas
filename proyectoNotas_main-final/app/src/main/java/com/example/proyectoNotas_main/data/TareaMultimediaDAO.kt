package com.example.proyectoNotas_main.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoNotas_main.model.TareaMultimedia
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaMultimediaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tareaMultimedia: TareaMultimedia)

    @Update
    suspend fun update(tareaMultimedia: TareaMultimedia)

    @Delete
    suspend fun delete(tareaMultimedia: TareaMultimedia)

    @Query("SELECT * from tareaMultimedia WHERE id = :id")
    fun getItem(id: Int): Flow<TareaMultimedia>

    @Query("SELECT * from tareaMultimedia WHERE tareaId = :tareaId")
    fun getAllById(tareaId: Int): Flow<List<TareaMultimedia>>

    @Query("SELECT * from tareaMultimedia")
    fun getAllItems(): Flow<List<TareaMultimedia>>
}