package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoutineEntity(
    val date : Int,
    val text : String,
    val isFinished : Boolean = false,
    val category : String = "",
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
)
