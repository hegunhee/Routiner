package routiner.core.domain.usecase.review

import hegunhee.routiner.model.Review
import routiner.core.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewOrNullByDateUseCase @Inject constructor(private val repository: ReviewRepository) {

    suspend operator fun invoke(date: Int): Review? {
        return repository.getReviewOrNullByDate(date)
    }

}
