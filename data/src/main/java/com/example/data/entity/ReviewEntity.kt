package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ReviewEntity(
    @PrimaryKey
    val date: Int,
    val review: String,
)