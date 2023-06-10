package com.example.domain.usecase.date

import com.example.domain.model.DayOfWeek
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDayOfWeekListByFlowUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke() : Flow<List<DayOfWeek>> {
        return repository.getAllDayOfWeekListByFlow()
    }
}