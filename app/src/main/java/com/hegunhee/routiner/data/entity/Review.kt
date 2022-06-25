package com.hegunhee.routiner.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Review(
    @PrimaryKey
    val date: Int,
    val review: String,
)