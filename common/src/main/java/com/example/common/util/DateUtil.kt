package com.example.common.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun getTodayDate(): Int =
    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()


@RequiresApi(Build.VERSION_CODES.O)
fun getTodayDayOfWeekFormatedKorean(): String =
    LocalDateTime.now().dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)