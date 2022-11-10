package com.example.domain.usecase.routine

import com.example.domain.repository.Repository
import com.hegunhee.routiner.domain.UseCase
import javax.inject.Inject

class DeleteAllRoutineByDateUseCase @Inject constructor(private val repository: Repository) :
    UseCase {

    suspend operator fun invoke(date : Int){
        repository.deleteAllRoutineByDate(date)
    }
}