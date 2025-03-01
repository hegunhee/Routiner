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

    override suspend fun insertRoutinesFromRepeatRoutineByDayOfWeek(dayOfWeek: String): List<Long> {
        val repeatRoutineList = localDataSource.getRepeatRoutines()
        repeatRoutineList.filter { it.dayOfWeekList.contains(dayOfWeek) }.toRoutineEntityList()
            .let { routineEntityList ->
                return localDataSource.insertRoutines(routineEntityList)
            }
    }

    override suspend fun insertRoutines(routineList: List<Routine>): List<Long> {
        return localDataSource.insertRoutines(routineList.toRoutineEntity())
    }

    override suspend fun insertRoutine(routine: Routine) {
        localDataSource.insertRoutine(routine.toRoutineEntity())
    }

    override fun getRoutinesFlowByDate(date: Int): Flow<List<Routine>> {
        return localDataSource.getRoutinesFlowByDate(date).map { it.toRoutineList() }
    }

    override suspend fun getRoutinesByDate(date: Int): List<Routine> {
        return localDataSource.getRoutinesByDate(date).toRoutineList()
    }

    override suspend fun updateRoutine(routine: Routine) {
        localDataSource.updateRoutine(routine.toRoutineEntity())
    }

    override suspend fun deleteRoutinesByDate(date: Int): Int {
        return localDataSource.deleteRoutinesByDate(date)
    }

    override suspend fun deleteRoutine(id: Int): Int {
        return localDataSource.deleteRoutine(id)
    }

}