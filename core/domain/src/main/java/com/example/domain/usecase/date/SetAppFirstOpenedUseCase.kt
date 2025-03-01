package com.example.domain.usecase.date

import com.example.domain.repository.SettingRepository
import javax.inject.Inject

class SetAppFirstOpenedUseCase @Inject constructor(private val repository: SettingRepository) {

    operator fun invoke() {
        repository.setAppFirstOpened()
    }
}