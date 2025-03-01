package com.example.domain.usecase.review

import hegunhee.routiner.model.Review
import com.example.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewOrNullByDateUseCase @Inject constructor(private val repository: ReviewRepository) {

    suspend operator fun invoke(date: Int): Review? {
        return repository.getReviewOrNullByDate(date)
    }

}
