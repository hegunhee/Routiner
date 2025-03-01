package com.example.domain.usecase.routine

import com.example.domain.repository.RoutineRepository
import javax.inject.Inject

class InsertRoutinesFromRepeatByDayOfWeekRoutineUseCase @Inject constructor(private val repository: RoutineRepository) {
    suspend operator fun invoke(dayOfWeek: String): List<Long> {
        return repository.insertRoutinesFromRepeatRoutineByDayOfWeek(dayOfWeek)
    }

}
