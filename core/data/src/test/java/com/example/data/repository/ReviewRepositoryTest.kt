package com.example.data.repository

import com.example.data.dataSource.local.DefaultLocalDataSource
import com.example.data.entity.ReviewEntity
import com.example.data.mapper.toReviewEntity
import com.example.domain.model.Review
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ReviewRepositoryTest {

    @InjectMocks
    private lateinit var sut: DefaultReviewRepository

    @Mock
    private lateinit var localDateSource: DefaultLocalDataSource

    private val date = 20250228

    @Test
    fun givenReviewDate_whenGetReview_thenWorksFine() {
        runBlocking {
            // given
            val reviewEntity = ReviewEntity(date, "")
            whenever(localDateSource.getReviewOrNullByDate(date)).thenReturn(reviewEntity)

            // when
            val review = sut.getReviewOrNullByDate(date)

            // then
            assertThat(review?.date).isEqualTo(date)
            verify(localDateSource).getReviewOrNullByDate(date)
        }
    }

    @Test
    fun givenNonExistentDate_whenGetReview_thenReturnsNull() {
        runBlocking {
            // given
            val nonExistentDate = -1
            whenever(localDateSource.getReviewOrNullByDate(nonExistentDate)).thenReturn(null)

            // when
            val review = sut.getReviewOrNullByDate(nonExistentDate)

            // then
            assertThat(review).isNull()
            verify(localDateSource).getReviewOrNullByDate(nonExistentDate)
        }
    }

    @Test
    fun givenReview_whenInsert_thenWorksFine() {
        runBlocking {
            // given
            val review = Review(date, "")
            whenever(localDateSource.insertReview(review.toReviewEntity())).thenReturn(Unit)

            // when
            sut.insertReview(review)

            // then
            verify(localDateSource).insertReview(review.toReviewEntity())
        }
    }

    @Test
    fun givenReview_whenDelete_thenWorksFine() {
        runBlocking {
            // given
            val review = Review(date, "")
            whenever(localDateSource.deleteReview(review.toReviewEntity())).thenReturn(1)

            // when
            val deleteCount = sut.deleteReview(review)

            // then
            assertThat(deleteCount).isEqualTo(listOf(review).size)
            verify(localDateSource).deleteReview(review.toReviewEntity())
        }
    }
}
