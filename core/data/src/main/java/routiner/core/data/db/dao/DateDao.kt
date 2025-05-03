package routiner.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import routiner.core.data.entity.DateEntity

@Dao
interface DateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDate(dateEntity: DateEntity)

    @Query("SELECT * FROM routineDate")
    suspend fun getDateList(): List<DateEntity>

}
