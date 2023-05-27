package com.example.domain.usecase.routine

import com.example.domain.model.Routine
import com.example.domain.repository.Repository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class InsertRoutineUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke(routineEntity: Routine){
        repository.insertRoutine(routineEntity)
    }
}