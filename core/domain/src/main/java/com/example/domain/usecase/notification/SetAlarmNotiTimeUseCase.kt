package com.example.domain.usecase.notification

import com.example.domain.repository.SettingRepository
import javax.inject.Inject

class SetAlarmNotiTimeUseCase @Inject constructor(private val repository: SettingRepository) {

    operator fun invoke(time : String){
        repository.setAlarmNotiTime(time)
    }
}