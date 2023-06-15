package com.example.domain.usecase.routine

import com.example.domain.model.Routine
import com.example.domain.repository.Repository
import com.example.domain.usecase.repeatRoutine.GetAllRepeatRoutineListUseCase
import javax.inject.Inject

class InsertAllDailyRoutineFromRepeatRoutineUseCase @Inject constructor(
    private val getAllRepeatRoutineListUseCase: GetAllRepeatRoutineListUseCase,
    private val repository: Repository
) {
    suspend operator fun invoke(dayOfWeek: String) {
        val list = getAllRepeatRoutineListUseCase().filter { it.dayOfWeekList.contains(dayOfWeek) }.map { Routine(getTodayDate(), it.text, category = it.category) }
        if (list.isNotEmpty()) {
            repository.insertAllRoutine(list)
        }
    }
}
}