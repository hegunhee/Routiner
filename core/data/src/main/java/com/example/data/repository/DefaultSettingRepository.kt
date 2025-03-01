package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.mapper.toNotiAlarm
import com.example.domain.model.AlarmTime
import com.example.domain.repository.SettingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSettingRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : SettingRepository {

    override suspend fun getCurrentDate(): Int {
        return localDataSource.getCurrentDate()
    }

    override suspend fun setCurrentDate(date: Int) {
        localDataSource.setCurrentDate(date)
    }

    override fun setAlarmNotiTime(time: String) {
        localDataSource.setAlarmNotiTime(time)
    }

    override fun getAlarmNotiTime(): AlarmTime {
        return localDataSource.getAlarmNotiTime().toNotiAlarm()
    }

    override fun isAppFirstOpened(): Boolean {
        return localDataSource.isAppFirstOpened()
    }

    override fun setAppFirstOpened() {
        localDataSource.setAppFirstOpened()
    }

    override fun getSortedDayOfWeekList(): List<String> {
        return listOf("월","화","수","목","금","토","일")
    }

}
