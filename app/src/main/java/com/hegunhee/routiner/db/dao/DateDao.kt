package com.hegunhee.routiner.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hegunhee.routiner.data.entity.Date

@Dao
interface DateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDate(date : Date)

    @Query("SELECT * FROM date")
    suspend fun getAllDate() : List<Date>
}