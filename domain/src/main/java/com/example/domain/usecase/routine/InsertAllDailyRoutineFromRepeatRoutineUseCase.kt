package com.example.domain.usecase.routine

import com.example.domain.model.Routine
import com.example.domain.repository.Repository
import com.example.domain.usecase.repeatRoutine.GetAllRepeatRoutineUseCase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class InsertAllDailyRoutineFromRepeatRoutineUseCase @Inject constructor(
    private val getAllRepeatRoutineUseCase: GetAllRepeatRoutineUseCase,
    private val repository: Repository
) {
    suspend operator fun invoke(dayOfWeek: String) {
        val list = getAllRepeatRoutineUseCase().filter { it.dayOfWeekList.contains(dayOfWeek) }.map { Routine(getTodayDate(), it.text, category = it.category) }
        if (list.isNotEmpty()) {
            repository.insertAllRoutine(list)
        }
    }
}
//TODO 리팩터링 필요
fun getTodayDate(): Int =
    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()