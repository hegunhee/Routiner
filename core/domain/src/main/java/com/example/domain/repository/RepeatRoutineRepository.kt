package com.example.domain.repository

import com.example.domain.model.RepeatRoutine
import kotlinx.coroutines.flow.Flow

interface RepeatRoutineRepository {

    suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutine)

    fun getRepeatRoutinesFlow(): Flow<List<RepeatRoutine>>

    suspend fun getRepeatRoutines(): List<RepeatRoutine>

    suspend fun deleteRepeatRoutine(text: String) : Int
}
