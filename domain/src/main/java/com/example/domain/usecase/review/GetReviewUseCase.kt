package com.example.domain.usecase.review

import com.example.domain.model.Review
import com.example.domain.repository.Repository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class GetReviewUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke(date : Int) : List<Review>{
        return repository.getReview(date)
    }
}