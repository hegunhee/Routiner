package com.hegunhee.routiner.model

import com.hegunhee.routiner.data.entity.Date
import com.hegunhee.routiner.data.entity.Review
import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.db.DateDao
import com.hegunhee.routiner.db.ReviewDao
import com.hegunhee.routiner.db.RoutineDao
import kotlinx.coroutines.flow.Flow

class DefaultRepository(
    private val routineDao: RoutineDao,
    private val dateDao: DateDao,
    private val reviewDao: ReviewDao
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

}