package com.ad_coding.noteappcourse.data.mapper

import com.ad_coding.noteappcourse.data.local.entity.TareaEntity
import com.ad_coding.noteappcourse.domain.model.Tarea

fun TareaEntity.asExternalModel(): Tarea = Tarea(
    id, date, title, content
)

fun Tarea.toEntity(): TareaEntity = TareaEntity(
    id, date, title, content
)