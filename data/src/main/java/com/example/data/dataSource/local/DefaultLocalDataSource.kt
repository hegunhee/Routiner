package com.example.data.dataSource.local

import com.example.data.db.SharedPreferenceManager
import com.example.data.db.dao.*
import com.example.data.entity.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultLocalDataSource @Inject constructor(
    private val routineDao: RoutineDao,
    private val dateDao: DateDao,
    private val reviewDao: ReviewDao,
    private val repeatRoutineDao: RepeatRoutineDao,
    private val categoryDao: CategoryDao,
    private val sharedPreferenceManager: SharedPreferenceManager
) : LocalDataSource {

    override fun getAllDailyRoutineByFlow(date: Int): Flow<List<RoutineEntity>> {
        return routineDao.getAllDailyRoutineListByFlow(date)
    }

    override suspend fun insertRoutine(routineEntity: RoutineEntity) {
        routineDao.insertRoutine(routineEntity)
    }

    override suspend fun deleteAllRoutineByDate(date: Int) {
        routineDao.deleteAllRoutineByDate(date)
    }

    override suspend fun deleteRoutine(id: Int) {
        routineDao.deleteRoutine(id)
    }

    override suspend fun updateRoutine(routineEntity: RoutineEntity) {
        routineDao.updateRoutine(routineEntity)
    }

    override suspend fun getRoutineListByDate(date: Int): List<RoutineEntity> {
        return routineDao.getRoutineListByDate(date)
    }

    override suspend fun insertDate(date: DateEntity) {
        return dateDao.insertDate(date)
    }

    override suspend fun getAllDateList(): List<DateEntity> {
        return dateDao.getAllDateList()
    }

    override suspend fun getReviewOrNullByDate(date: Int): ReviewEntity? {
        return reviewDao.getReviewOrNullByDate(date)
    }

    override suspend fun insertReview(review: ReviewEntity) {
        reviewDao.insertReview(review)
    }

    override suspend fun deleteReview(review: ReviewEntity) {
        reviewDao.deleteReview(review)
    }

    override suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutineEntity) {
        repeatRoutineDao.insertRepeatRoutine(repeatRoutine)
    }

    override fun getAllRepeatRoutineListByFlow(): Flow<List<RepeatRoutineEntity>> {
        return repeatRoutineDao.getAllRepeatRoutineListByFlow()
    }

    override suspend fun getAllRepeatRoutineList(): List<RepeatRoutineEntity> {
        return repeatRoutineDao.getAllRepeatRoutineList()
    }

    override suspend fun deleteRepeatRoutine(text: String) {
        repeatRoutineDao.deleteRepeatRoutine(text)
    }

    override suspend fun insertAllRoutine(routineList: List<RoutineEntity>) {
        routineDao.insertAllRoutine(routineList)
    }

    override suspend fun insertCategory(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    override fun getAllCategoryListByFlow(): Flow<List<CategoryEntity>> {
        return categoryDao.getAllCategoryListByFlow()
    }

    override suspend fun getCurrentDate(): Int {
        return sharedPreferenceManager.getCurrentDate()
    }

    override suspend fun setCurrentDate(date: Int) {
        sharedPreferenceManager.setCurrentDate(date)
    }

    override fun setNotiSendValue(notiValue: Boolean) {
        sharedPreferenceManager.setNotiSendValue(notiValue)
    }

    override fun getNotiSendValue(): Boolean {
        return sharedPreferenceManager.getNotiSendValue()
    }

    override fun isAppFirstOpened(): Boolean {
        return sharedPreferenceManager.isAppFirstOpened()
    }

    override fun setAppFirstOpened() {
        return sharedPreferenceManager.setAppFirstOpened()
    }
}