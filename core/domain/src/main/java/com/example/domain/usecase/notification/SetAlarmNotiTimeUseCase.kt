package com.example.domain.usecase.notification

import com.example.domain.repository.Repository
import javax.inject.Inject

class SetAlarmNotiTimeUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(time : String){
        repository.setAlarmNotiTime(time)
    }
}