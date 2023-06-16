package com.example.domain.usecase.date

import com.example.domain.model.Date
import com.example.domain.repository.Repository
import javax.inject.Inject


class GetAllDateListUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() : List<Date> {
        return repository.getAllDateList()
    }
}