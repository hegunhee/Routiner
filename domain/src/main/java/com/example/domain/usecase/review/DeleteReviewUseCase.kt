package com.example.domain.usecase.review

import com.example.domain.model.Review
import com.example.domain.repository.Repository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class DeleteReviewUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke(review : Review) {
        repository.deleteReview(review)
    }
}