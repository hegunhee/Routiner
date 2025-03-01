package com.example.domain.repository

import com.example.domain.model.Routine
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {

    suspend fun insertRoutinesFromRepeatRoutineByDayOfWeek(dayOfWeek : String) : List<Long>

    suspend fun insertRoutines(routineList: List<Routine>) : List<Long>

    suspend fun insertRoutine(routine: Routine)

    fun getRoutinesFlowByDate(date : Int) : Flow<List<Routine>>

    suspend fun getRoutinesByDate(date :Int) : List<Routine>

    suspend fun updateRoutine(routine : Routine)

    suspend fun deleteRoutinesByDate(date : Int) : Int

    suspend fun deleteRoutine(id : Int) : Int

}