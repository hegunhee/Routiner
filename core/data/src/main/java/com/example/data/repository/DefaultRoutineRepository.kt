package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.mapper.toRoutineEntity
import com.example.data.mapper.toRoutineEntityList
import com.example.data.mapper.toRoutineList
import com.example.domain.model.Routine
import com.example.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRoutineRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : RoutineRepository {

    override suspend fun insertAllDailyRoutineFromRepeatRoutine(dayOfWeek: String) {
        val repeatRoutineList = localDataSource.getRepeatRoutines()
        repeatRoutineList.filter { it.dayOfWeekList.contains(dayOfWeek) }.toRoutineEntityList().let { routineEntityList ->
            localDataSource.insertRoutines(routineEntityList)
        }
    }

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

}