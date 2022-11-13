package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepeatRoutineEntity(
    @PrimaryKey var text : String = "",
    val dayOfWeekList: List<String>,
    val category : String = ""
)
