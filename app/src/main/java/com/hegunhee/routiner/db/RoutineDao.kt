package com.hegunhee.routiner.db

import androidx.room.*
import com.hegunhee.routiner.data.entity.Routine
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyRoutine(routine: Routine)

    @Query("SELECT * FROM routine WHERE date = :date")
    fun getDailyRoutineByFlow(date : Int) : Flow<List<Routine>>

    @Query("SELECT * FROM routine WHERE date = :date")
    suspend fun getRoutineListByDate(date : Int) : List<Routine>

    @Query("DELETE FROM routine WHERE date = :date")
    suspend fun deleteAllRoutineByDate(date : Int)

    @Query("DELETE FROM routine WHERE id = :id")
    suspend fun deleteRoutine(id : Int)
}