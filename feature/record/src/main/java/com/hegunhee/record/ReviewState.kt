package com.hegunhee.record

import com.example.domain.model.Review


sealed class ReviewState {
    object Uninitalized : ReviewState()

    data class Success(val review: Review) : ReviewState()

    object Empty : ReviewState()
}
