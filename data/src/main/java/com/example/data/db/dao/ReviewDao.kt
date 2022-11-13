package com.example.data.db.dao

import androidx.room.*
import com.example.data.entity.ReviewEntity

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDate(reviewEntity: ReviewEntity)

    @Query("SELECT * FROM ReviewEntity WHERE date == :date")
    suspend fun getReview(date : Int) : List<ReviewEntity>

    @Delete
    fun deleteReview(reviewEntity : ReviewEntity)
}