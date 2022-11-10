package com.example.domain.usecase.routine

import com.example.domain.repository.Repository
import com.hegunhee.routiner.domain.UseCase
import javax.inject.Inject
class DeleteRoutineUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke(id : Int){
        repository.deleteRoutine(id)
    }
}