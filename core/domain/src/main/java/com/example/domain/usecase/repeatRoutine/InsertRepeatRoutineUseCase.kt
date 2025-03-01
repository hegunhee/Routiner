package com.example.domain.usecase.repeatRoutine

import hegunhee.routiner.model.RepeatRoutine
import com.example.domain.repository.RepeatRoutineRepository
import javax.inject.Inject

class InsertRepeatRoutineUseCase @Inject constructor(private val repository: RepeatRoutineRepository) {

    suspend operator fun invoke(repeatRoutine: RepeatRoutine){
        repository.insertRepeatRoutine(repeatRoutine)
    }
}