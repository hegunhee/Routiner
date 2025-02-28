package com.example.data.db.dao

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.db.InMemoryDBProvider.provideInMemoryDB
import com.example.data.db.RoutinerDatabase
import com.example.data.entity.ReviewEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReviewDaoTest {

    private lateinit var sut: ReviewDao
    private lateinit var db: RoutinerDatabase

    @Before
    fun initDB() {
        db = provideInMemoryDB(ApplicationProvider.getApplicationContext())
        sut = db.reviewDao()
    }

    @Throws
    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun givenEntity_whenInserting_thenWorksFine() {
        runBlocking {
            // given
            val date = 20250227
            val entity = createReviewEntity(date, "")

            // when
            sut.insertReview(entity)
            val insertingEntity = sut.getReviewOrNullByDate(date)

            // then
            assertEquals(insertingEntity, entity)
        }
    }

    @Test
    fun givenNonExistentDate_whenGetting_thenReturnsNull() {
        runBlocking {
            // given
            val date = 20250227

            // when
            val entity = sut.getReviewOrNullByDate(date)

            // then
            assertNull(entity)
        }
    }

    @Test
    fun givenEntity_whenDeleting_thenWorksFine() {
        runBlocking {
            // given
            val date = 20250227
            val entity = createReviewEntity(date, "")
            sut.insertReview(entity)

            // when
            val deleteCount = sut.deleteReview(entity)

            // then
            assertEquals(deleteCount, 1)
        }
    }

    private fun createReviewEntity(date: Int, text: String): ReviewEntity {
        return ReviewEntity(date, text)
    }
}
