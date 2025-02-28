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

    override suspend fun insertRoutine(routineEntity: RoutineEntity) {
        routineDao.insertRoutine(routineEntity)
    }

    override suspend fun insertRoutines(routineList: List<RoutineEntity>): List<Long> {
        return routineDao.insertRoutines(routineList)
    }

    override fun getRoutinesFlowByDate(date: Int): Flow<List<RoutineEntity>> {
        return routineDao.getRoutinesFlowByDate(date)
    }

    override suspend fun getRoutinesByDate(date: Int): List<RoutineEntity> {
        return routineDao.getRoutinesByDate(date)
    }

    override suspend fun deleteRoutine(id: Int): Int {
        return routineDao.deleteRoutine(id)
    }

    override suspend fun deleteRoutinesByDate(date: Int): Int {
        return routineDao.deleteRoutinesByDate(date)
    }

    override suspend fun updateRoutine(routineEntity: RoutineEntity) {
        routineDao.updateRoutine(routineEntity)
    }


    override suspend fun insertDate(date: DateEntity) {
        return dateDao.insertDate(date)
    }

    override suspend fun getDateList(): List<DateEntity> {
        return dateDao.getDateList()
    }


    override suspend fun insertReview(review: ReviewEntity) {
        reviewDao.insertReview(review)
    }

    override suspend fun getReviewOrNullByDate(date: Int): ReviewEntity? {
        return reviewDao.getReviewOrNullByDate(date)
    }

    override suspend fun deleteReview(review: ReviewEntity) : Int {
        return reviewDao.deleteReview(review)
    }


    override suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutineEntity) {
        repeatRoutineDao.insertRepeatRoutine(repeatRoutine)
    }

    override fun getRepeatRoutinesFlow(): Flow<List<RepeatRoutineEntity>> {
        return repeatRoutineDao.getRepeatRoutinesFlow()
    }

    override suspend fun getRepeatRoutines(): List<RepeatRoutineEntity> {
        return repeatRoutineDao.getRepeatRoutines()
    }

    override suspend fun deleteRepeatRoutine(text: String) : Int {
        return repeatRoutineDao.deleteRepeatRoutine(text)
    }


    override suspend fun insertCategory(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    override fun getAllCategoryListByFlow(): Flow<List<CategoryEntity>> {
        return categoryDao.getAllCategoryListByFlow()
    }

    override suspend fun deleteCategory(categoryEntity: CategoryEntity) {
        categoryDao.removeCategory(categoryEntity)
    }

    override suspend fun getCurrentDate(): Int {
        return sharedPreferenceManager.getCurrentDate()
    }

    override suspend fun setCurrentDate(date: Int) {
        sharedPreferenceManager.setCurrentDate(date)
    }

    override fun setAlarmNotiTime(time: String) {
        sharedPreferenceManager.setAlarmNotiTime(time)
    }

    override fun getAlarmNotiTime(): String {
        return sharedPreferenceManager.getAlarmNotiTime()
    }

    override fun isAppFirstOpened(): Boolean {
        return sharedPreferenceManager.isAppFirstOpened()
    }

    override fun setAppFirstOpened() {
        return sharedPreferenceManager.setAppFirstOpened()
    }

}
