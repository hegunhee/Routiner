package com.example.domain.usecase.date

import com.example.domain.repository.SettingRepository
import javax.inject.Inject

class GetCurrentDateUseCase @Inject constructor(private val repository: SettingRepository) {

    suspend operator fun invoke(): Int {
        return repository.getCurrentDate()
    }

}
