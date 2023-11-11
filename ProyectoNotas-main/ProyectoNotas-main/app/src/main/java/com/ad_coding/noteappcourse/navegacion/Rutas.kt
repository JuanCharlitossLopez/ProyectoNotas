package com.ad_coding.noteappcourse.navegacion

sealed class Rutas (var rutas: String){
    object Note:Rutas("rutasNotas")
    object Tareas:Rutas("rutasTareas")

}