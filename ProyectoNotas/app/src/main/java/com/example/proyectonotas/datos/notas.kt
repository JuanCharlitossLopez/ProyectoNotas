package com.example.proyectonotas.datos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyectonotas.utils.Constants

@Entity(tableName = Constants.note_table_name)
data class notas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("Tittle")
    val titulo: String,
    @ColumnInfo("Date")
    val fecha: Int,
    @ColumnInfo("Info")
    val descripcion: String

)
