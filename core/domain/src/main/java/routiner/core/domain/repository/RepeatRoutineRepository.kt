package routiner.core.domain.repository

import routiner.core.model.RepeatRoutine
import kotlinx.coroutines.flow.Flow

interface RepeatRoutineRepository {

    suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutine)

    fun getRepeatRoutinesFlow(): Flow<List<RepeatRoutine>>

    suspend fun getRepeatRoutines(): List<RepeatRoutine>

    suspend fun deleteRepeatRoutine(text: String) : Int
}
