package com.example.domain.usecase.repeatRoutine

import com.example.domain.model.RepeatRoutine
import com.example.domain.repository.Repository
import com.hegunhee.routiner.domain.UseCase
import javax.inject.Inject

class GetAllRepeatRoutineUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke() : List<RepeatRoutine>{
        return repository.getAllRepeatRoutine()
    }
}