package com.hegunhee.routiner.model

import com.hegunhee.routiner.data.entity.Routine
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllDailyRoutine(date : Int) : Flow<List<Routine>>

    suspend fun insertDailyRoutine(routine: Routine)
}