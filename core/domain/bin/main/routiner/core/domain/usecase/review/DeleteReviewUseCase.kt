package routiner.core.domain.usecase.review

import hegunhee.routiner.model.Review
import routiner.core.domain.repository.ReviewRepository
import javax.inject.Inject

class DeleteReviewUseCase @Inject constructor(private val repository: ReviewRepository) {

    suspend operator fun invoke(review: Review): Int {
        return repository.deleteReview(review)
    }

}
