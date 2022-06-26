package com.hegunhee.routiner.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentDate() : Int = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()