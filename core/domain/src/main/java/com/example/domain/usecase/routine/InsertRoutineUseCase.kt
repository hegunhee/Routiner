package com.example.domain.usecase.routine

import hegunhee.routiner.model.Routine
import com.example.domain.repository.RoutineRepository
import javax.inject.Inject

class InsertRoutineUseCase @Inject constructor(private val repository: RoutineRepository) {

    suspend operator fun invoke(routine: Routine){
        repository.insertRoutine(routine)
    }
}