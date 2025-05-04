package routiner.feature.record

import routiner.core.model.Review

sealed interface ReviewState {

    data object Loading : ReviewState

    data class Exist(
        val review: Review,
    ) : ReviewState

    data object Revise : ReviewState

    data object Empty : ReviewState

}
