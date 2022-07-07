package com.hegunhee.routiner.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hegunhee.routiner.data.entity.RepeatRoutine
import kotlinx.coroutines.flow.Flow

@Dao
interface RepeatRoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutine)

    @Query("SELECT * FROM RepeatRoutine")
    fun getAllRepeatRoutineByFlow() : Flow<List<RepeatRoutine>>

    @Query("SELECT * FROM RepeatRoutine")
    suspend fun getAllRepeatRoutine() : List<RepeatRoutine>
}