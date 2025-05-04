package routiner.core.data.repository

import routiner.core.data.datasource.local.LocalDataSource
import routiner.core.data.mapper.toNotiAlarm
import hegunhee.routiner.model.AlarmTime
import routiner.core.domain.repository.SettingRepository
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
