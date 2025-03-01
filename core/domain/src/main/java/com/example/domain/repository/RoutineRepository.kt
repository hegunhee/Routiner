package com.example.domain.repository

import com.example.domain.model.Routine
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {

    suspend fun insertAllDailyRoutineFromRepeatRoutine(dayOfWeek : String)

    suspend fun insertAllRoutine(routineList: List<Routine>)

    suspend fun insertRoutine(routine: Routine)

    fun getAllDailyRoutineByFlow(date : Int) : Flow<List<Routine>>

    suspend fun getRoutineListByDate(date :Int) : List<Routine>

    suspend fun updateRoutine(routine : Routine)

    suspend fun deleteAllRoutineByDate(date : Int)

    suspend fun deleteRoutine(id : Int)

}