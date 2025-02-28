package com.example.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "repeatRoutine",
    indices = [Index(value = ["category"])]
)
data class RepeatRoutineEntity(
    @PrimaryKey
    val text : String = "",
    val dayOfWeekList: List<String>,
    val category : String = ""
)
