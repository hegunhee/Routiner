package com.hegunhee.routiner.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(@PrimaryKey val name : String)