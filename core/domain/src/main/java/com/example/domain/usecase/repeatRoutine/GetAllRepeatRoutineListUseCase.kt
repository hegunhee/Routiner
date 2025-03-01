package com.example.domain.usecase.repeatRoutine

import com.example.domain.model.RepeatRoutine
import com.example.domain.repository.RepeatRoutineRepository
import javax.inject.Inject

class GetAllRepeatRoutineListUseCase @Inject constructor(private val repository: RepeatRoutineRepository) {

    suspend operator fun invoke() : List<RepeatRoutine>{
        return repository.getAllRepeatRoutineList()
    }
}