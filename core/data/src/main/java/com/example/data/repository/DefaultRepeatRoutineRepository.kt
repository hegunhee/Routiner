package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.mapper.toRepeatRoutineEntity
import com.example.data.mapper.toRepeatRoutineList
import hegunhee.routiner.model.RepeatRoutine
import com.example.domain.repository.RepeatRoutineRepository
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
