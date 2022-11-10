package com.example.domain.usecase.repeatRoutine

import com.example.domain.model.RepeatRoutine
import com.example.domain.repository.Repository
import com.hegunhee.routiner.domain.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRepeatRoutineByFlowUseCase @Inject constructor(private val repository: Repository) :
    UseCase {

    operator fun invoke(): Flow<List<RepeatRoutine>> {
        return repository.getAllRepeatRoutineByFlow()
    }
}