package com.example.proyectonotas.datos

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [notas::class], version = 1)
abstract class NotasDatabase : RoomDatabase() {
    abstract fun notasDAO(): NotasDAO
}