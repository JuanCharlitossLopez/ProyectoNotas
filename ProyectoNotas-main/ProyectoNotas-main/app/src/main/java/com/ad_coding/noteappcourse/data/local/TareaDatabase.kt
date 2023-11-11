package com.ad_coding.noteappcourse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ad_coding.noteappcourse.data.local.dao.TareaDao
import com.ad_coding.noteappcourse.data.local.entity.TareaEntity

@Database(
    version = 1,
    entities = [TareaEntity::class]
)

abstract class TareaDatabase: RoomDatabase() {

    abstract val dao: TareaDao

    companion object {
        const val name = "tarea_db"
    }
}