package com.hegunhee.routiner.model

import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.db.RoutineDao
import kotlinx.coroutines.flow.Flow

class DefaultRepository(private val routineDao : RoutineDao) : Repository {
    override fun getAllDailyRoutine(date: Int): Flow<List<Routine>> {
        return routineDao.getDailyRoutine(date)
    }

    override suspend fun insertDailyRoutine(routine: Routine) {
        routineDao.insertDailyRoutine(routine)
    }
}