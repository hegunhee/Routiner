package com.example.domain.usecase.routine

import com.example.domain.model.Routine
import com.example.domain.repository.Repository
import javax.inject.Inject

class GetRoutineListByDateUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(date :Int) : List<Routine>{
        return repository.getRoutineListByDate(date)
    }
}