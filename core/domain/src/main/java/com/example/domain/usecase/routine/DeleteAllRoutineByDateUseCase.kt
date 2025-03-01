package com.example.domain.usecase.routine

import com.example.domain.repository.Repository
import com.example.domain.repository.RoutineRepository
import javax.inject.Inject

class DeleteAllRoutineByDateUseCase @Inject constructor(private val repository: RoutineRepository) {

    suspend operator fun invoke(date : Int){
        repository.deleteAllRoutineByDate(date)
    }
}