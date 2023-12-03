package com.example.proyectoNotas_main.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tareaMultimedia",
    foreignKeys = [ForeignKey(entity = Tarea::class,
        parentColumns = ["id"],
        childColumns = ["tareaId"],
        onDelete = ForeignKey.CASCADE)])
data class TareaMultimedia(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "uri") var uri: String,
    @ColumnInfo(name = "descripcion") var descripcion: String,
    @ColumnInfo(name = "tareaId") var tareaId: Int,
    @ColumnInfo(name = "tipo") var tipo: String
)