package com.example.domain.usecase.date

import com.example.domain.repository.Repository
import javax.inject.Inject


class GetAppFirstOpenedUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke() : Boolean{
        return repository.isAppFirstOpened()
    }
}