package com.example.domain.usecase.notification

import hegunhee.routiner.model.AlarmTime
import com.example.domain.repository.SettingRepository
import javax.inject.Inject

class GetAlarmNotiTimeUseCase @Inject constructor(private val repository: SettingRepository) {

    operator fun invoke() : AlarmTime {
        return repository.getAlarmNotiTime()
    }
}