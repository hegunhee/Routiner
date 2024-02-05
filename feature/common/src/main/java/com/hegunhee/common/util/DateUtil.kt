package com.hegunhee.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

// TODO 추후 :core:util 모듈로 이전
fun getTodayDate(): Int {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()
}

fun getTodayDayOfWeekFormatedKorean(): String  {
    return LocalDateTime.now().dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)
}