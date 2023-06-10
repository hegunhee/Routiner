package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.DayOfWeekEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DayOfWeekDao {

    @Query("SELECT * FROM DayOfWeekEntity")
    fun getAllDayOfWeekListByFlow() : Flow<List<DayOfWeekEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDefaultDayOfWeekList(dayOfWeekList : List<DayOfWeekEntity>)
}