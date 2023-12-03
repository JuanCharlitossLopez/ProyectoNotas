package com.example.proyectoNotas_main.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "notaMultimedia",
    foreignKeys = [ForeignKey(entity = Nota::class,
        parentColumns = ["id"],
        childColumns = ["notaId"],
        onDelete = ForeignKey.CASCADE)])
data class NotaMultimedia(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "uri") var uri: String,
    @ColumnInfo(name = "descripcion") var descripcion: String,
    @ColumnInfo(name = "notaId") var notaId: Int,
    @ColumnInfo(name = "tipo") var tipo: String
)