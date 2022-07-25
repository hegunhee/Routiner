package com.hegunhee.routiner.db.dao

import androidx.room.*
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

    @Query("DELETE FROM RepeatRoutine WHERE text = :text")
    suspend fun deleteRepeatRoutine(text : String)

}