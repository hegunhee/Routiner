package com.hegunhee.routiner.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hegunhee.routiner.data.entity.*
import com.hegunhee.routiner.db.dao.DateDao
import com.hegunhee.routiner.db.dao.RepeatRoutineDao
import com.hegunhee.routiner.db.dao.ReviewDao
import com.hegunhee.routiner.db.dao.RoutineDao

@Database(entities = [Routine::class,Date::class,Review::class,RepeatRoutine::class,Category::class], version = 1)
@TypeConverters(MyTypeConverters::class)
abstract class RoutinerDatabase : RoomDatabase(){

    abstract fun routineDao() : RoutineDao

    abstract fun dateDao() : DateDao

    abstract fun reviewDao() : ReviewDao

    abstract fun repeatRoutineDao() : RepeatRoutineDao

    companion object{
        const val APP_NAME = "routinerDatabase.db"
    }
}