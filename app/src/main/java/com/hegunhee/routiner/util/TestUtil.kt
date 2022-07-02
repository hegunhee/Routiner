package com.hegunhee.routiner.util

import java.text.DecimalFormat
import java.time.LocalDateTime

fun getCurrentTestDayInfo() : LocalDateTime = LocalDateTime.now()

fun getDateFormat(day : Int) = DecimalFormat("00").format(day)