package com.example.data.repository

import com.example.data.db.SharedPreferenceManager
import com.example.data.db.dao.*
import com.example.domain.model.*
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultRepository(
    private val routineDao: RoutineDao,
    private val dateDao: DateDao,
    private val reviewDao: ReviewDao,
    private val repeatRoutineDao: RepeatRoutineDao,
    private val categoryDao: CategoryDao,
    private val sharedPreferenceManager: SharedPreferenceManager
) : Repository {
    override fun getAllDailyRoutineByFlow(date: Int): Flow<List<Routine>> {
        return routineDao.getDailyRoutineByFlow(date).map { it.toRoutineList() }
    }

    override suspend fun insertDailyRoutine(routine: Routine) {
        routineDao.insertDailyRoutine(routine.toRoutineEntity())
    }

    override suspend fun deleteAllRoutineByDate(date: Int) {
        routineDao.deleteAllRoutineByDate(date)
    }

    override suspend fun deleteRoutine(id: Int) {
        routineDao.deleteRoutine(id)
    }

    override suspend fun getRoutineListByDate(date: Int): List<Routine> {
        return routineDao.getRoutineListByDate(date).toRoutineList()
    }

    override suspend fun insertDate(date: Date) {
        return dateDao.insertDate(date.toDateEntity())
    }

    override suspend fun getAllDate(): List<Date> {
        return dateDao.getAllDate().toDateList()
    }

    override suspend fun getReview(date: Int): List<Review> {
        return reviewDao.getReview(date).toReviewList()
    }

    override suspend fun insertReview(review: Review) {
        reviewDao.insertDate(review.toReviewEntity())
    }

    override suspend fun deleteReview(review: Review) {
        reviewDao.deleteReview(review.toReviewEntity())
    }

    override suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutine) {
        repeatRoutineDao.insertRepeatRoutine(repeatRoutine.toRepeatRoutineEntity())
    }

    override fun getAllRepeatRoutineByFlow(): Flow<List<RepeatRoutine>> {
        return repeatRoutineDao.getAllRepeatRoutineByFlow().map { it.toRepeatRoutineList() }
    }

    override suspend fun getAllRepeatRoutine(): List<RepeatRoutine> {
        return repeatRoutineDao.getAllRepeatRoutine().toRepeatRoutineList()
    }

    override suspend fun deleteRepeatRoutine(text: String) {
        repeatRoutineDao.deleteRepeatRoutine(text)
    }

    override suspend fun insertAllRoutine(routineList: List<Routine>) {
        routineDao.insertAllDailyRoutine(routineList.toRoutineEntity())
    }

    override suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(category.toCategoryEntity())
    }

    override suspend fun getAllCategory(): List<Category> {
        return categoryDao.getAllCategory().toCategory()
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
        return sharedPreferenceManager.setNofiSendValue(notiValue)
    }

    override fun getNotiSendValue(): Boolean {
        return sharedPreferenceManager.getNotiSendValue()
    }


}