package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routineDate")
data class DateEntity(@PrimaryKey val date : Int)
