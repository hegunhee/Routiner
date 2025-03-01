package com.example.domain.usecase.date

import com.example.domain.model.Date
import com.example.domain.repository.DateRepository
import javax.inject.Inject


class GetAllDateListUseCase @Inject constructor(private val repository: DateRepository) {

    suspend operator fun invoke() : List<Date> {
        return repository.getAllDateList()
    }
}