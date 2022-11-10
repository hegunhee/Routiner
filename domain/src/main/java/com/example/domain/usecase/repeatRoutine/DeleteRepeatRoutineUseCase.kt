package com.example.domain.usecase.repeatRoutine

import com.example.domain.repository.Repository
import com.hegunhee.routiner.domain.UseCase
import javax.inject.Inject

class DeleteRepeatRoutineUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke(text: String) {
        repository.deleteRepeatRoutine(text)
    }
}