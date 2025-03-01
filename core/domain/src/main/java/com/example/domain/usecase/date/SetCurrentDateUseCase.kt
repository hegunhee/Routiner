package com.example.domain.usecase.date

import com.example.domain.repository.SettingRepository
import javax.inject.Inject

class SetCurrentDateUseCase @Inject constructor(private val repository: SettingRepository) {

    suspend operator fun invoke(date: Int) {
        repository.setCurrentDate(date)
    }

}
