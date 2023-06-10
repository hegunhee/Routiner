package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DayOfWeekEntity(
    @PrimaryKey val date : String,
    val isSelected : Boolean = false)