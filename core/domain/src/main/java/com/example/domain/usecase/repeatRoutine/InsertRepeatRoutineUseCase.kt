package com.example.domain.usecase.repeatRoutine

import com.example.domain.model.RepeatRoutine
import com.example.domain.repository.Repository
import javax.inject.Inject

class InsertRepeatRoutineUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(repeatRoutine: RepeatRoutine){
        repository.insertRepeatRoutine(repeatRoutine)
    }
}