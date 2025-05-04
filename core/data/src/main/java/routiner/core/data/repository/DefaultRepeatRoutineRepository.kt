package routiner.core.data.repository

import routiner.core.data.datasource.local.LocalDataSource
import routiner.core.data.mapper.toRepeatRoutineEntity
import routiner.core.data.mapper.toRepeatRoutineList
import routiner.core.model.RepeatRoutine
import routiner.core.domain.repository.RepeatRoutineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepeatRoutineRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : RepeatRoutineRepository {

    override suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutine) {
        localDataSource.insertRepeatRoutine(repeatRoutine.toRepeatRoutineEntity())
    }

    override fun getRepeatRoutinesFlow(): Flow<List<RepeatRoutine>> {
        return localDataSource.getRepeatRoutinesFlow().map { it.toRepeatRoutineList() }
    }

    override suspend fun getRepeatRoutines(): List<RepeatRoutine> {
        return localDataSource.getRepeatRoutines().toRepeatRoutineList()
    }

    override suspend fun deleteRepeatRoutine(text: String) : Int {
        return localDataSource.deleteRepeatRoutine(text)
    }

}
