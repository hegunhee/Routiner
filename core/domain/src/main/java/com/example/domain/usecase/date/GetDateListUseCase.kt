package com.example.domain.usecase.date

import hegunhee.routiner.model.Date
import com.example.domain.repository.DateRepository
import javax.inject.Inject


class GetDateListUseCase @Inject constructor(private val repository: DateRepository) {

    suspend operator fun invoke(): List<Date> {
        return repository.getDateList()
    }

}
