package com.example.domain.usecase.repeatRoutine

import hegunhee.routiner.model.RepeatRoutine
import com.example.domain.repository.RepeatRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepeatRoutinesFlowUseCase @Inject constructor(private val repository: RepeatRoutineRepository) {

    operator fun invoke(): Flow<List<RepeatRoutine>> {
        return repository.getRepeatRoutinesFlow()
    }

}
