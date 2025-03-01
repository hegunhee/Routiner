package com.example.domain.repository

import com.example.domain.model.RepeatRoutine
import kotlinx.coroutines.flow.Flow

interface RepeatRoutineRepository {

    suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutine)

    fun getAllRepeatRoutineListByFlow(): Flow<List<RepeatRoutine>>

    suspend fun getAllRepeatRoutineList(): List<RepeatRoutine>

    suspend fun deleteRepeatRoutine(text: String)
}
