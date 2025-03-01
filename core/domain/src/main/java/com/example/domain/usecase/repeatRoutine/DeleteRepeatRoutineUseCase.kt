package com.example.domain.usecase.repeatRoutine

import com.example.domain.repository.RepeatRoutineRepository
import javax.inject.Inject

class DeleteRepeatRoutineUseCase @Inject constructor(private val repository: RepeatRoutineRepository) {

    suspend operator fun invoke(text: String): Int {
        return repository.deleteRepeatRoutine(text)
    }

}
