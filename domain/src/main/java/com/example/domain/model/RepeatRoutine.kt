package com.example.domain.model

data class RepeatRoutine(
    var text : String = "",
    val dayOfWeekList: List<String>,
    val category : String = ""
)
