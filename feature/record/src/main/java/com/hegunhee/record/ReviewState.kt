package com.hegunhee.record

import hegunhee.routiner.model.Review

sealed interface ReviewState {

    data object Loading : ReviewState

    data class Exist(
        val review: Review,
    ) : ReviewState

    data object Revise : ReviewState

    data object Empty : ReviewState

}
