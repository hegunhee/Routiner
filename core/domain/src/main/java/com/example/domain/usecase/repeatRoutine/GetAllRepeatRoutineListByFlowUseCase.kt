package com.example.domain.usecase.repeatRoutine

import com.example.domain.model.RepeatRoutine
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRepeatRoutineListByFlowUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<List<RepeatRoutine>> {
        return repository.getAllRepeatRoutineListByFlow()
    }
}