package com.ad_coding.noteappcourse.ui.screen.tarea


interface TareaEvent {
    data class TitleChange(val value: String): TareaEvent
    data class ContentChange(val value: String): TareaEvent
    data class DateChange(val value: String): TareaEvent
    object Save : TareaEvent
    object NavigateBack : TareaEvent
    object DeleteTarea : TareaEvent
}