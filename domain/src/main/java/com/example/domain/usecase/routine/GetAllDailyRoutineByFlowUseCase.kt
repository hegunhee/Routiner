package com.example.domain.usecase.routine

import com.example.domain.model.Routine
import com.example.domain.repository.Repository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDailyRoutineByFlowUseCase @Inject constructor(private val repository: Repository) :
    UseCase {

    operator fun invoke(date : Int) : Flow<List<Routine>> {
        return repository.getAllDailyRoutineByFlow(date)
    }
}