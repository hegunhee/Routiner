package com.hegunhee.routiner.util

import java.time.LocalDateTime
import java.time.ZoneId

object Time {

    fun toTimeMills(hour : Int,minute : Int) : Long {
        return LocalDateTime.now().withHour(hour).withMinute(minute).atZone(ZoneId.systemDefault()).toInstant().epochSecond
    }
}