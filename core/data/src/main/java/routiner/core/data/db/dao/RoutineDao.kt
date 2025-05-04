package routiner.core.data.db.dao

import androidx.room.*
import routiner.core.data.entity.RoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutines(routineEntityList: List<RoutineEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutine(routineEntity: RoutineEntity)

    @Query("SELECT DISTINCT date FROM Routine ORDER BY date ASC")
    suspend fun getDistinctDateList() : List<Int>

    @Query("SELECT * FROM routine WHERE date = :date")
    fun getRoutinesFlowByDate(date: Int): Flow<List<RoutineEntity>>

    @Query("SELECT * FROM routine WHERE date = :date")
    suspend fun getRoutinesByDate(date: Int): List<RoutineEntity>

    @Query("DELETE FROM routine WHERE date = :date")
    suspend fun deleteRoutinesByDate(date: Int) : Int

    @Query("DELETE FROM routine WHERE id = :id")
    suspend fun deleteRoutine(id: Int) : Int

    @Update
    suspend fun updateRoutine(routineEntity: RoutineEntity)

}
