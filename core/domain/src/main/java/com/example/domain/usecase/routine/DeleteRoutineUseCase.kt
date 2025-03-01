package com.example.domain.usecase.routine

import com.example.domain.repository.RoutineRepository
import javax.inject.Inject

class DeleteRoutineUseCase @Inject constructor(private val repository: RoutineRepository) {

    suspend operator fun invoke(id : Int){
        repository.deleteRoutine(id)
    }
}