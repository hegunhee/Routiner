package com.example.domain.usecase.routine

import com.example.domain.repository.RoutineRepository
import javax.inject.Inject

class InsertAllDailyRoutineFromRepeatRoutineUseCase @Inject constructor(private val repository: RoutineRepository) {
    suspend operator fun invoke(dayOfWeek: String) {
        repository.insertRoutinesFromRepeatRoutineByDayOfWeek(dayOfWeek)
    }
}