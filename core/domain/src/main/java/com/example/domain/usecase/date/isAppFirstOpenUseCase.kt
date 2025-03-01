package com.example.domain.usecase.date

import com.example.domain.repository.SettingRepository
import javax.inject.Inject


class IsAppFirstOpenUseCase @Inject constructor(private val repository: SettingRepository) {

    operator fun invoke() : Boolean{
        return repository.isAppFirstOpened()
    }
}