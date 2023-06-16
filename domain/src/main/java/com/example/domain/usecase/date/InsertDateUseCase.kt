package com.example.domain.usecase.date

import com.example.domain.repository.Repository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class InsertDateUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke(date : Int) {
        repository.insertDate(date))
    }
}