package routiner.core.data.repository

import routiner.core.data.datasource.local.LocalDataSource
import routiner.core.data.mapper.toReviewEntity
import routiner.core.data.mapper.toReviewOrNull
import routiner.core.model.Review
import routiner.core.domain.repository.ReviewRepository
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

    override suspend fun deleteReview(review: Review) : Int {
        return localDataSource.deleteReview(review.toReviewEntity())
    }

}
