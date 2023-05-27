package com.example.data.db.dao

import androidx.room.*
import com.example.data.entity.RoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoutine(routineEntityList : List<RoutineEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutine(routineEntity: RoutineEntity)

    @Query("SELECT * FROM RoutineEntity WHERE date = :date")
    fun getAllDailyRoutineListByFlow(date : Int) : Flow<List<RoutineEntity>>

    @Query("SELECT * FROM RoutineEntity WHERE date = :date")
    suspend fun getRoutineListByDate(date : Int) : List<RoutineEntity>

    @Query("DELETE FROM RoutineEntity WHERE date = :date")
    suspend fun deleteAllRoutineByDate(date : Int)

    @Query("DELETE FROM RoutineEntity WHERE id = :id")
    suspend fun deleteRoutine(id : Int)


}