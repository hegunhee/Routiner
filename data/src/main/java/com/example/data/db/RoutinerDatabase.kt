package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.db.dao.*
import com.example.data.entity.*

@Database(entities = [RoutineEntity::class,DateEntity::class,ReviewEntity::class,RepeatRoutineEntity::class, CategoryEntity::class,DayOfWeekEntity::class], version = 1)
@TypeConverters(MyTypeConverters::class)
abstract class RoutinerDatabase : RoomDatabase(){

    abstract fun routineDao() : RoutineDao

    abstract fun dateDao() : DateDao

    abstract fun reviewDao() : ReviewDao

    abstract fun repeatRoutineDao() : RepeatRoutineDao

    abstract fun categoryDao() : CategoryDao

    abstract fun dayOfWeekDao() : DayOfWeekDao

    companion object{
        const val APP_NAME = "routinerDatabase.db"
    }
}