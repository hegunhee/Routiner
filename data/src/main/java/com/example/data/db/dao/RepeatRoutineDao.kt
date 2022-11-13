package com.example.data.db.dao

import androidx.room.*
import com.example.data.entity.RepeatRoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepeatRoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepeatRoutine(repeatRoutineEntity: RepeatRoutineEntity)

    @Query("SELECT * FROM RepeatRoutineEntity")
    fun getAllRepeatRoutineByFlow() : Flow<List<RepeatRoutineEntity>>

    @Query("SELECT * FROM RepeatRoutineEntity")
    suspend fun getAllRepeatRoutine() : List<RepeatRoutineEntity>

    @Query("DELETE FROM RepeatRoutineEntity WHERE text = :text")
    suspend fun deleteRepeatRoutine(text : String)

}