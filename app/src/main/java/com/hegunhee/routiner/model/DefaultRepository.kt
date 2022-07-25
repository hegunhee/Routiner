package com.hegunhee.routiner.model

import com.hegunhee.routiner.data.entity.*
import com.hegunhee.routiner.db.dao.*
import kotlinx.coroutines.flow.Flow

class DefaultRepository(
    private val routineDao: RoutineDao,
    private val dateDao: DateDao,
    private val reviewDao: ReviewDao,
    private val repeatRoutineDao: RepeatRoutineDao,
    private val categoryDao: CategoryDao
) : Repository {
    override fun getAllDailyRoutineByFlow(date: Int): Flow<List<Routine>> {
        return routineDao.getDailyRoutineByFlow(date)
    }

    override suspend fun insertDailyRoutine(routine: Routine) {
        routineDao.insertDailyRoutine(routine)
    }

    override suspend fun deleteAllRoutineByDate(date: Int) {
        routineDao.deleteAllRoutineByDate(date)
    }

    override suspend fun deleteRoutine(id: Int) {
        routineDao.deleteRoutine(id)
    }

    override suspend fun getRoutineListByDate(date: Int): List<Routine> {
        return routineDao.getRoutineListByDate(date)
    }

    override suspend fun insertDate(date: Date) {
        return dateDao.insertDate(date)
    }

    override suspend fun getAllDate(): List<Date> {
        return dateDao.getAllDate()
    }

    override suspend fun getReview(date: Int): List<Review> {
        return reviewDao.getReview(date)
    }

    override suspend fun insertReview(review: Review) {
        reviewDao.insertDate(review)
    }

    override suspend fun deleteReview(review: Review) {
        reviewDao.deleteReview(review)
    }

    override suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutine) {
        repeatRoutineDao.insertRepeatRoutine(repeatRoutine)
    }

    override fun getAllRepeatRoutineByFlow(): Flow<List<RepeatRoutine>> {
        return repeatRoutineDao.getAllRepeatRoutineByFlow()
    }

    override suspend fun getAllRepeatRoutine(): List<RepeatRoutine> {
        return repeatRoutineDao.getAllRepeatRoutine()
    }

    override suspend fun deleteRepeatRoutine(text: String) {
        repeatRoutineDao.deleteRepeatRoutine(text)
    }

    override suspend fun insertAllRoutine(routineList: List<Routine>) {
        routineDao.insertAllDailyRoutine(routineList)
    }

    override suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(category)
    }


}