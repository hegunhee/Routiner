package com.example.domain.usecase.routine

import hegunhee.routiner.model.Routine
import com.example.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDailyRoutineByFlowUseCase @Inject constructor(private val repository: RoutineRepository) {

    operator fun invoke(date : Int) : Flow<List<Routine>> {
        return repository.getRoutinesFlowByDate(date)
    }
}