package com.hegunhee.routiner.domain

import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.model.Repository
import com.hegunhee.routiner.util.getTodayDate
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class InsertAllDailyRoutineFromRepeatRoutineUseCase @Inject constructor(
    private val getAllRepeatRoutineUseCase: GetAllRepeatRoutineUseCase,
    private val repository: Repository
) {
    suspend operator fun invoke(dayOfWeek: String) {
        val list = getAllRepeatRoutineUseCase().filter { it.dayOfWeekList.contains(dayOfWeek) }.map { Routine(getTodayDate(), it.text) }
        if (list.isNotEmpty()) {
            repository.insertAllRoutine(list)
        }
    }
}