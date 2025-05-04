package routiner.core.data.repository

import routiner.core.data.datasource.local.LocalDataSource
import routiner.core.data.mapper.toRoutineEntity
import routiner.core.data.mapper.toRoutineEntityList
import routiner.core.data.mapper.toRoutineList
import hegunhee.routiner.model.Routine
import routiner.core.domain.repository.RoutineRepository
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