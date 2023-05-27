package com.example.data.dataSource.local

import com.example.data.db.SharedPreferenceManager
import com.example.data.db.dao.*
import com.example.data.entity.*
import com.example.data.mapper.*
import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
        return routineDao.getDailyRoutineByFlow(date)
    }

    override suspend fun insertRoutine(routineEntity: RoutineEntity) {
        routineDao.insertDailyRoutine(routineEntity)
    }

    override suspend fun deleteAllRoutineByDate(date: Int) {
        routineDao.deleteAllRoutineByDate(date)
    }

    override suspend fun deleteRoutine(id: Int) {
        routineDao.deleteRoutine(id)
    }

    override suspend fun getRoutineListByDate(date: Int): List<RoutineEntity> {
        return routineDao.getRoutineListByDate(date)
    }

    override suspend fun insertDate(date: DateEntity) {
        return dateDao.insertDate(date)
    }

    override suspend fun getAllDateList(): List<DateEntity> {
        return dateDao.getAllDate()
    }

    override suspend fun getReviewListByDate(date: Int): List<ReviewEntity> {
        return reviewDao.getReview(date)
    }

    override suspend fun insertReview(review: ReviewEntity) {
        reviewDao.insertDate(review)
    }

    override suspend fun deleteReview(review: ReviewEntity) {
        reviewDao.deleteReview(review)
    }

    override suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutineEntity) {
        repeatRoutineDao.insertRepeatRoutine(repeatRoutine)
    }

    override fun getAllRepeatRoutineListByFlow(): Flow<List<RepeatRoutineEntity>> {
        return repeatRoutineDao.getAllRepeatRoutineByFlow()
    }

    override suspend fun getAllRepeatRoutineList(): List<RepeatRoutineEntity> {
        return repeatRoutineDao.getAllRepeatRoutine()
    }

    override suspend fun deleteRepeatRoutine(text: String) {
        repeatRoutineDao.deleteRepeatRoutine(text)
    }

    override suspend fun insertAllRoutine(routineList: List<RoutineEntity>) {
        routineDao.insertAllDailyRoutine(routineList)
    }

    override suspend fun insertCategory(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    override fun getAllCategoryListByFlow(): Flow<List<CategoryEntity>> {
        return categoryDao.getAllCategoryByFlow()
    }

    override suspend fun getCurrentDate(): Int {
        return sharedPreferenceManager.getCurrentDate()
    }

    override suspend fun setCurrentDate(date: Int) {
        sharedPreferenceManager.setCurrentDate(date)
    }

    override suspend fun getDefaultCurrentDate(): Int {
        return SharedPreferenceManager.CURRENT_DATE_DEFAULT_DATE
    }

    override fun setNotiSendValue(notiValue: Boolean) {
        sharedPreferenceManager.setNofiSendValue(notiValue)
    }

    override fun getNotiSendValue(): Boolean {
        return sharedPreferenceManager.getNotiSendValue()
    }

}