package com.example.domain.repository

import com.example.domain.model.AlarmTime

interface SettingRepository {

    suspend fun getCurrentDate() : Int

    suspend fun setCurrentDate(date : Int)

    fun setAlarmNotiTime(time : String)

    fun getAlarmNotiTime() : AlarmTime

    fun isAppFirstOpened() : Boolean

    fun setAppFirstOpened()

    fun getSortedDayOfWeekList() : List<String>

}
