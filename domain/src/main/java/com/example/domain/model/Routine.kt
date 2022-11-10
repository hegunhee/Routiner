package com.example.domain.model

data class Routine(
    val date : Int,
    val text : String,
    val isFinished : Boolean = false,
    val category : String = "",
    var id : Int = 0
)