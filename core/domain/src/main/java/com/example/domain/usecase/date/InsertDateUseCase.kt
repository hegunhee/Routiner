package com.example.domain.usecase.date

import com.example.domain.repository.DateRepository
import javax.inject.Inject

class InsertDateUseCase @Inject constructor(private val repository: DateRepository) {

    suspend operator fun invoke(date: Int) {
        repository.insertDate(date)
    }

}
