package com.example.domain.usecase.routine

import hegunhee.routiner.model.Routine
import com.example.domain.repository.RoutineRepository
import javax.inject.Inject

class GetRoutinesByDateUseCase @Inject constructor(private val repository: RoutineRepository) {

    suspend operator fun invoke(date: Int): List<Routine> {
        return repository.getRoutinesByDate(date)
    }

}
