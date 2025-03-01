package com.example.domain.usecase.date

import com.example.domain.repository.SettingRepository
import javax.inject.Inject

class GetSortedDayOfWeekListUseCase @Inject constructor(private val repository: SettingRepository) {

    operator fun invoke() : List<String> {
        return repository.getSortedDayOfWeekList()
    }

}
