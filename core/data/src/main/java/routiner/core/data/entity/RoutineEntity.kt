package routiner.core.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "routine",
    indices = [Index(value = ["date","category"])]
)
data class RoutineEntity(
    val date: Int,
    val text: String,
    val isFinished: Boolean = false,
    val category: String = "",
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
)
