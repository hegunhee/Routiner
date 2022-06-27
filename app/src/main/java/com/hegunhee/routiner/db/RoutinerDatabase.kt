package com.hegunhee.routiner.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hegunhee.routiner.data.entity.Date
import com.hegunhee.routiner.data.entity.Review
import com.hegunhee.routiner.data.entity.Routine

@Database(entities = [Routine::class,Date::class,Review::class], version = 1)
abstract class RoutinerDatabase : RoomDatabase(){

    abstract fun routineDao() : RoutineDao

    abstract fun dateDao() : DateDao

    abstract fun reviewDao() : ReviewDao

    companion object{
        const val APP_NAME = "routinerDatabase.db"
    }



}