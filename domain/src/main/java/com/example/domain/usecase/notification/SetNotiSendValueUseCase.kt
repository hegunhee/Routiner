package com.example.domain.usecase.notification

import com.example.domain.repository.Repository
import javax.inject.Inject

class SetNotiSendValueUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(notiValue : Boolean){
        repository.setNotiSendValue(notiValue)
    }
}