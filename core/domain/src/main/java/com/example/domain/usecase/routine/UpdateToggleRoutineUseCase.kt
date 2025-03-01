package com.example.domain.usecase.routine

import com.example.domain.model.Routine
import com.example.domain.repository.Repository
import com.example.domain.repository.RoutineRepository
import javax.inject.Inject

class UpdateToggleRoutineUseCase @Inject constructor(private val repository: RoutineRepository) {

    suspend operator fun invoke(routine : Routine) {
        repository.updateRoutine(routine)
    }
}