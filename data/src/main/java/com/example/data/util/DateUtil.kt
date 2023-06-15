package com.example.data.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getTodayDate() : Int = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()

