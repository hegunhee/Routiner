package com.example.domain.usecase.review

import com.example.domain.model.Review
import com.example.domain.repository.Repository
import javax.inject.Inject

class GetReviewOrNullByDateUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(date : Int) : Review?{
        return repository.getReviewOrNullByDate(date)
    }
}