package com.example.domain.usecase.date

import com.example.domain.repository.Repository
import javax.inject.Inject

class InsertDateUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(date : Int) {
        repository.insertDate(date)
    }
}