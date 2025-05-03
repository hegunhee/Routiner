package routiner.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import routiner.core.data.db.dao.CategoryDao
import routiner.core.data.db.dao.DateDao
import routiner.core.data.db.dao.RepeatRoutineDao
import routiner.core.data.db.dao.ReviewDao
import routiner.core.data.db.dao.RoutineDao
import routiner.core.data.entity.CategoryEntity
import routiner.core.data.entity.DateEntity
import routiner.core.data.entity.RepeatRoutineEntity
import routiner.core.data.entity.ReviewEntity
import routiner.core.data.entity.RoutineEntity

@Database(entities = [RoutineEntity::class, DateEntity::class, ReviewEntity::class, RepeatRoutineEntity::class, CategoryEntity::class], version = 1)
@TypeConverters(MyTypeConverters::class)
abstract class RoutinerDatabase : RoomDatabase(){

    abstract fun routineDao() : RoutineDao

    abstract fun dateDao() : DateDao

    abstract fun reviewDao() : ReviewDao

    abstract fun repeatRoutineDao() : RepeatRoutineDao

    abstract fun categoryDao() : CategoryDao

    companion object{
        const val APP_NAME = "routinerDatabase.db"
    }
}