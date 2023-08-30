package com.hegunhee.common.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

fun getTodayDate(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()
    }else {
        val currentDate = getCurrentKoreanCalender().time
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
        return dateFormat.format(currentDate).toInt()
    }
}




fun getTodayDayOfWeekFormatedKorean(): String  {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now().dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)
    }else {
        val calendar = getCurrentKoreanCalender()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val dayOfWeekList = listOf<String>("", "일", "월", "화", "수", "목", "금", "토")
        return dayOfWeekList[dayOfWeek]
    }
}

private fun getCurrentKoreanCalender() : Calendar  {
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    calendar.add(Calendar.HOUR_OF_DAY,9)
    return calendar
}
