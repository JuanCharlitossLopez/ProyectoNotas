package com.example.proyectoNotas_main.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tareas")
data class Tarea (
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo(name = "fechaACompletar") val fechaACompletar: String,
    @ColumnInfo(name = "isComplete") var isComplete: Boolean,
    @ColumnInfo(name = "contenido") var contenido: String,
    @ColumnInfo(name = "imageUris") var imageUris: String,
    @ColumnInfo(name = "videoUris") var videoUris: String
)
