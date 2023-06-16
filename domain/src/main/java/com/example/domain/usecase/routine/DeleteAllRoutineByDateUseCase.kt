package com.example.domain.usecase.routine

import com.example.domain.repository.Repository
import javax.inject.Inject

class DeleteAllRoutineByDateUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(date : Int){
        repository.deleteAllRoutineByDate(date)
    }
}