package com.example.domain.usecase.notification

import com.example.domain.repository.Repository
import javax.inject.Inject

class GetNotiSendValueUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() : Boolean{
        return repository.getNotiSendValue()
    }
}