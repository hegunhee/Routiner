package com.example.domain.usecase.date

import com.example.domain.repository.Repository
import javax.inject.Inject

class GetSortedDayOfWeekListUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke() : List<String> {
        return repository.getSortedDayOfWeekList()
    }
}