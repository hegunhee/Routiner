package com.hegunhee.routiner.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepeatRoutine(
    @PrimaryKey var text : String = "",
    val dayOfWeekList: List<String>,
    val category : String = ""
)
