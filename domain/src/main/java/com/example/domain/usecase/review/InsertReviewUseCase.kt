package com.example.domain.usecase.review

import com.example.domain.model.Review
import com.example.domain.repository.Repository
import javax.inject.Inject

class InsertReviewUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(review:Review){
        repository.insertReview(review)
    }
}