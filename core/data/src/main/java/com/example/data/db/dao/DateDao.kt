package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.DateEntity

@Dao
interface DateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDate(dateEntity: DateEntity)

    @Query("SELECT * FROM routineDate")
    suspend fun getAllDateList(): List<DateEntity>

}
