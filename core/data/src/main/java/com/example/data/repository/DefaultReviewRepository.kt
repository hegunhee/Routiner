package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.mapper.toReviewEntity
import com.example.data.mapper.toReviewOrNull
import com.example.domain.model.Review
import com.example.domain.repository.ReviewRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultReviewRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : ReviewRepository {

    override suspend fun getReviewOrNullByDate(date: Int): Review? {
        return localDataSource.getReviewOrNullByDate(date).toReviewOrNull()
    }

    override suspend fun insertReview(review: Review) {
        localDataSource.insertReview(review.toReviewEntity())
    }

    override suspend fun deleteReview(review: Review) {
        localDataSource.deleteReview(review.toReviewEntity())
    }

}
