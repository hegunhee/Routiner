package com.example.domain.usecase.repeatRoutine

import com.example.domain.model.RepeatRoutine
import com.example.domain.repository.Repository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class GetAllRepeatRoutineListUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke() : List<RepeatRoutine>{
        return repository.getAllRepeatRoutineList()
    }
}