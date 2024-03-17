package com.example.domain.usecase.routine

import com.example.domain.repository.Repository
import javax.inject.Inject

class InsertAllDailyRoutineFromRepeatRoutineUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(dayOfWeek: String) {
        repository.insertAllDailyRoutineFromRepeatRoutine(dayOfWeek)
    }
}