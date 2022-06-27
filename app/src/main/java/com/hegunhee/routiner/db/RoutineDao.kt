package com.hegunhee.routiner.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hegunhee.routiner.data.entity.Routine
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Insert
    suspend fun insertDailyRoutine(routine: Routine)

    @Query("SELECT * FROM routine WHERE date = :date")
    fun getDailyRoutine(date : Int) : Flow<List<Routine>>

    @Query("DELETE FROM routine WHERE date = :date")
    suspend fun deleteAllRoutineByDate(date : Int)
}