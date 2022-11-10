package com.example.domain.usecase.date

import com.example.domain.model.Date
import com.example.domain.repository.Repository
import com.hegunhee.routiner.domain.UseCase
import javax.inject.Inject


class GetAllDateUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke() : List<Date> {
        return repository.getAllDate()
    }
}