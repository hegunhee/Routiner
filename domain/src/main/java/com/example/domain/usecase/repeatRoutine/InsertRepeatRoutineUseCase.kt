package com.example.domain.usecase.repeatRoutine

import com.example.domain.model.RepeatRoutine
import com.example.domain.repository.Repository
import com.hegunhee.routiner.domain.UseCase
import javax.inject.Inject

class InsertRepeatRoutineUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke(repeatRoutineEntity: RepeatRoutine){
        repository.insertRepeatRoutine(repeatRoutineEntity)
    }
}