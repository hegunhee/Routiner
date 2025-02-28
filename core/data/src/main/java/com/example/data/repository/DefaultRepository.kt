package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.mapper.*
import com.example.domain.model.*
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun insertAllRoutine(routineList: List<Routine>) {
        localDataSource.insertRoutines(routineList.toRoutineEntity())
    }

    override suspend fun insertRoutine(routine: Routine) {
        localDataSource.insertRoutine(routine.toRoutineEntity())
    }

    override fun getAllDailyRoutineByFlow(date: Int): Flow<List<Routine>> {
        return localDataSource.getRoutinesFlowByDate(date).map { it.toRoutineList() }
    }

    override suspend fun getRoutineListByDate(date: Int): List<Routine> {
        return localDataSource.getRoutinesByDate(date).toRoutineList()
    }

    override suspend fun updateRoutine(routine: Routine) {
        localDataSource.updateRoutine(routine.toRoutineEntity())
    }

    override suspend fun deleteAllRoutineByDate(date: Int) {
        localDataSource.deleteRoutinesByDate(date)
    }

    override suspend fun deleteRoutine(id: Int) {
        localDataSource.deleteRoutine(id)
    }


    override suspend fun insertDate(date: Int) {
        return localDataSource.insertDate(Date(date).toDateEntity())
    }

    override suspend fun getAllDateList(): List<Date> {
        return localDataSource.getAllDateList().toDateList()
    }


    override suspend fun getReviewOrNullByDate(date: Int): Review? {
        return localDataSource.getReviewOrNullByDate(date).toReviewOrNull()
    }

    override suspend fun insertReview(review: Review) {
        localDataSource.insertReview(review.toReviewEntity())
    }

    override suspend fun deleteReview(review: Review) {
        localDataSource.deleteReview(review.toReviewEntity())
    }


    override suspend fun insertAllDailyRoutineFromRepeatRoutine(dayOfWeek: String) {
        val repeatRoutineList = localDataSource.getAllRepeatRoutineList()
        repeatRoutineList.filter { it.dayOfWeekList.contains(dayOfWeek) }.toRoutineEntityList().let { routineEntityList ->
            localDataSource.insertRoutines(routineEntityList)
        }
    }

    override suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutine) {
        localDataSource.insertRepeatRoutine(repeatRoutine.toRepeatRoutineEntity())
    }

    override fun getAllRepeatRoutineListByFlow(): Flow<List<RepeatRoutine>> {
        return localDataSource.getAllRepeatRoutineListByFlow().map { it.toRepeatRoutineList() }
    }

    override suspend fun getAllRepeatRoutineList(): List<RepeatRoutine> {
        return localDataSource.getAllRepeatRoutineList().toRepeatRoutineList()
    }

    override suspend fun deleteRepeatRoutine(text: String) {
        localDataSource.deleteRepeatRoutine(text)
    }


    override suspend fun insertCategory(category: Category) {
        localDataSource.insertCategory(category.toCategoryEntity())
    }

    override fun getAllCategoryListByFlow(): Flow<List<Category>> {
        return localDataSource.getAllCategoryListByFlow().map { it.toCategory() }
    }

    override suspend fun deleteCategory(category: Category) {
        localDataSource.deleteCategory(category.toCategoryEntity())
    }


    override suspend fun getCurrentDate(): Int {
        return localDataSource.getCurrentDate()
    }

    override suspend fun setCurrentDate(date: Int) {
        localDataSource.setCurrentDate(date)
    }

    override fun setAlarmNotiTime(time: String) {
        return localDataSource.setAlarmNotiTime(time)
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
