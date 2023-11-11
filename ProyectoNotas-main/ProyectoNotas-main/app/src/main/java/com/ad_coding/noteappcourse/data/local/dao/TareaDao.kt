package com.ad_coding.noteappcourse.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ad_coding.noteappcourse.data.local.entity.TareaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {
    @Query("SELECT * FROM TareaEntity")
    fun getAllTareas(): Flow<List<TareaEntity>>

    @Query("""
        SELECT * FROM TareaEntity
        WHERE id = :id
    """)
    suspend fun getTareaById(id: Int): TareaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTarea(tareaEntity: TareaEntity)

    @Delete
    suspend fun deleteTarea(tareaEntity: TareaEntity)

    @Update
    suspend fun updateTarea(tareaEntity: TareaEntity)
}