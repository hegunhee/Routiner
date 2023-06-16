package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.entity.DayOfWeekEntity
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
    override fun getAllDailyRoutineByFlow(date: Int): Flow<List<Routine>> {
        return localDataSource.getAllDailyRoutineByFlow(date).map { it.toRoutineList() }
    }

    override suspend fun insertRoutine(routine: Routine) {
        localDataSource.insertRoutine(routine.toRoutineEntity())
    }

    override suspend fun deleteAllRoutineByDate(date: Int) {
        localDataSource.deleteAllRoutineByDate(date)
    }

    override suspend fun deleteRoutine(id: Int) {
        localDataSource.deleteRoutine(id)
    }

    override suspend fun updateRoutine(routine: Routine) {
        localDataSource.updateRoutine(routine.toRoutineEntity())
    }

    override suspend fun getRoutineListByDate(date: Int): List<Routine> {
        return localDataSource.getRoutineListByDate(date).toRoutineList()
    }

    override suspend fun insertDate(date: Date) {
        return localDataSource.insertDate(date.toDateEntity())
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

    override suspend fun insertAllRoutine(routineList: List<Routine>) {
        localDataSource.insertAllRoutine(routineList.toRoutineEntity())
    }

    override suspend fun insertAllDailyRoutineFromRepeatRoutine(dayOfWeek: String) {
        val repeatRoutineList = localDataSource.getAllRepeatRoutineList()
        repeatRoutineList.filter { it.dayOfWeekList.contains(dayOfWeek) }.toRoutineEntityList().let { routineEntityList ->
            localDataSource.insertAllRoutine(routineEntityList)
        }
    }

    override suspend fun insertCategory(category: Category) {
        localDataSource.insertCategory(category.toCategoryEntity())
    }

    override fun getAllCategoryListByFlow(): Flow<List<Category>> {
        return localDataSource.getAllCategoryListByFlow().map { it.toCategory() }
    }

    override suspend fun getCurrentDate(): Int {
        return localDataSource.getCurrentDate()
    }

    override suspend fun setCurrentDate(date: Int) {
        localDataSource.setCurrentDate(date)
    }

    override fun setNotiSendValue(notiValue: Boolean) {
        return localDataSource.setNotiSendValue(notiValue)
    }

    override fun getNotiSendValue(): Boolean {
        return localDataSource.getNotiSendValue()
    }

    override fun isAppFirstOpened(): Boolean {
        return localDataSource.isAppFirstOpened()
    }

    override fun setAppFirstOpened() {
        localDataSource.setAppFirstOpened()
    }

    override fun getAllDayOfWeekListByFlow(): Flow<List<DayOfWeek>> {
        return localDataSource.getAllDayOfWeekListByFlow().map { it.toDayOfWeekList() }
    }

    override suspend fun insertDefaultDayOfWeekList() {
        localDataSource.insertDefaultDayOfWeekList(getDefaultDayOfWeekList())
    }

    private fun getDefaultDayOfWeekList() : List<DayOfWeekEntity>{
        return listOf<DayOfWeekEntity>(DayOfWeekEntity("월"), DayOfWeekEntity("화"),DayOfWeekEntity("수"),DayOfWeekEntity("목"),DayOfWeekEntity("금"),DayOfWeekEntity("토"),DayOfWeekEntity("일"))
    }


}