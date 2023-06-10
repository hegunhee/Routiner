package com.example.domain.usecase.date

import com.example.domain.repository.Repository
import javax.inject.Inject

class InsertDefaultDayOfWeekListUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() {
        repository.insertDefaultDayOfWeekList()
    }
}