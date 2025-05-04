package routiner.core.data.db.dao

import androidx.room.*
import routiner.core.data.entity.RepeatRoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepeatRoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepeatRoutine(repeatRoutineEntity: RepeatRoutineEntity)

    @Query("SELECT * FROM repeatRoutine")
    fun getRepeatRoutinesFlow(): Flow<List<RepeatRoutineEntity>>

    @Query("SELECT * FROM repeatRoutine")
    suspend fun getRepeatRoutines(): List<RepeatRoutineEntity>

    @Query("DELETE FROM repeatRoutine WHERE text = :text")
    suspend fun deleteRepeatRoutine(text: String) : Int

}
