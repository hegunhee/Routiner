package com.example.domain.usecase.review

import hegunhee.routiner.model.Review
import com.example.domain.repository.ReviewRepository
import javax.inject.Inject

class DeleteReviewUseCase @Inject constructor(private val repository: ReviewRepository) {

    suspend operator fun invoke(review: Review): Int {
        return repository.deleteReview(review)
    }

}
