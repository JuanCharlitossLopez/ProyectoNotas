package com.ad_coding.noteappcourse.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TareaEntity (
    @PrimaryKey val id: Int?,
    val date: String,
    val title: String,
    val content: String
)