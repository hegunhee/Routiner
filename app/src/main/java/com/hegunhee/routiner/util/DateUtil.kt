package com.hegunhee.routiner.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

fun getTodayDate(): Int =
    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()


fun getTodayDayOfWeekFormatedKorean(): String =
    LocalDateTime.now().dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)