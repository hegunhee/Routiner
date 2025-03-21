package com.example.data.db.dao

import androidx.room.*
import com.example.data.entity.ReviewEntity

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(reviewEntity: ReviewEntity)

    @Query("SELECT * FROM reviews WHERE date == :date")
    suspend fun getReviewOrNullByDate(date: Int): ReviewEntity?

    @Delete
    suspend fun deleteReview(reviewEntity: ReviewEntity) : Int

}
