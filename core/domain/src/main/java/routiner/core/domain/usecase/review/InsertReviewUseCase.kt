package routiner.core.domain.usecase.review

import routiner.core.model.Review
import routiner.core.domain.repository.ReviewRepository
import javax.inject.Inject

class InsertReviewUseCase @Inject constructor(private val repository: ReviewRepository) {

    suspend operator fun invoke(review: Review) {
        repository.insertReview(review)
    }
}
