package com.example.domain.repository

import hegunhee.routiner.model.Review

interface ReviewRepository {

    suspend fun getReviewOrNullByDate(date : Int) : Review?

    suspend fun insertReview(review : Review)

    suspend fun deleteReview(review: Review) : Int

}