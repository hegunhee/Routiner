package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.mapper.toNotiAlarm
import com.example.domain.model.AlarmTime
import com.example.domain.repository.SettingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSettingRepository @Inject constructor(
    private val localDateSource: LocalDataSource
) : SettingRepository {

    override suspend fun getCurrentDate(): Int {
        return localDateSource.getCurrentDate()
    }

    override suspend fun setCurrentDate(date: Int) {
        localDateSource.setCurrentDate(date)
    }

    override fun setAlarmNotiTime(time: String) {
        localDateSource.setAlarmNotiTime(time)
    }

    override fun getAlarmNotiTime(): AlarmTime {
        return localDateSource.getAlarmNotiTime().toNotiAlarm()
    }

    override fun isAppFirstOpened(): Boolean {
        return localDateSource.isAppFirstOpened()
    }

    override fun setAppFirstOpened() {
        localDateSource.setAppFirstOpened()
    }

    override fun getSortedDayOfWeekList(): List<String> {
        return listOf("월","화","수","목","금","토","일")
    }

}
