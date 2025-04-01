package com.example.domain.usecase.date

import com.example.domain.repository.DateRepository
import hegunhee.routiner.model.Date
import javax.inject.Inject

class GetRoutineExistDateListUseCase @Inject constructor(
    private val dateRepository: DateRepository,
) {

    suspend operator fun invoke() : List<Date> {
        return dateRepository.getRoutineExistDateList()
    }
}