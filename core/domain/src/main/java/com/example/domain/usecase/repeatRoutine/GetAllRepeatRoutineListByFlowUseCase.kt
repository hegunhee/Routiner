package com.example.domain.usecase.repeatRoutine

import com.example.domain.model.RepeatRoutine
import com.example.domain.repository.RepeatRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRepeatRoutineListByFlowUseCase @Inject constructor(private val repository: RepeatRoutineRepository) {

    operator fun invoke(): Flow<List<RepeatRoutine>> {
        return repository.getRepeatRoutinesFlow()
    }
}