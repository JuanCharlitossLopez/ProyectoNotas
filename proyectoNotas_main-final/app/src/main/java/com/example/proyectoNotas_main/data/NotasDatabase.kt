package com.example.proyectoNotas_main.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectoNotas_main.model.Nota
import com.example.proyectoNotas_main.model.NotaMultimedia

@Database(entities = [Nota::class, NotaMultimedia::class], version = 6, exportSchema = false)
abstract class NotasDatabase : RoomDatabase() {
    abstract fun notaDao(): NotaDAO
    abstract fun notaMultimediaDao(): NotaMultimediaDAO

    companion object {
        @Volatile
        private var Instance: NotasDatabase? = null

        fun getDatabase(context: Context): NotasDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NotasDatabase::class.java, "notas_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
