package com.example.domain.usecase.date

import com.example.domain.repository.Repository
import javax.inject.Inject

class SetAppFirstOpenedUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke() {
        repository.setAppFirstOpened()
    }
}