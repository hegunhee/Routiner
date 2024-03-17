package com.example.domain.usecase.repeatRoutine

import com.example.domain.repository.Repository
import javax.inject.Inject

class DeleteRepeatRoutineUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(text: String) {
        repository.deleteRepeatRoutine(text)
    }
}