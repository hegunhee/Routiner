package com.hegunhee.routiner.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hegunhee.routiner.data.entity.Date

@Dao
interface DateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDate(date : Date)
}