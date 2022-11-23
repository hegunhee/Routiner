package com.example.domain.usecase.date

import com.example.domain.repository.Repository
import javax.inject.Inject

class GetDefaultCurrentDateUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() : Int {
        return repository.getDefaultCurrentDate()
    }
}