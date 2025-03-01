package com.hegunhee.record

import hegunhee.routiner.model.Review


sealed interface ReviewState {

    fun isReviewEmpty() : Boolean

    object Uninitalized : ReviewState {
        override fun isReviewEmpty(): Boolean = true
    }

    data class Success(val review: Review) : ReviewState {
        override fun isReviewEmpty(): Boolean = false
    }

    object Empty : ReviewState {
        override fun isReviewEmpty(): Boolean = true
    }

    object Revise: ReviewState {
        override fun isReviewEmpty(): Boolean = true
    }
}
