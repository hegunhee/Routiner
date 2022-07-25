package com.hegunhee.routiner.db.dao

import androidx.room.*
import com.hegunhee.routiner.data.entity.Review

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDate(review: Review)

    @Query("SELECT * FROM review WHERE date == :date")
    suspend fun getReview(date : Int) : List<Review>

    @Delete
    fun deleteReview(review : Review)
}