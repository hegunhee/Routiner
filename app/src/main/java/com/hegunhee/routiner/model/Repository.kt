package com.hegunhee.routiner.model

import com.hegunhee.routiner.data.entity.Date
import com.hegunhee.routiner.data.entity.RepeatRoutine
import com.hegunhee.routiner.data.entity.Review
import com.hegunhee.routiner.data.entity.Routine
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllDailyRoutineByFlow(date : Int) : Flow<List<Routine>>

    suspend fun insertDailyRoutine(routine: Routine)

    suspend fun deleteAllRoutineByDate(date : Int)

    suspend fun deleteRoutine(id : Int)

    suspend fun getRoutineListByDate(date :Int) : List<Routine>

    suspend fun insertDate(date : Date)

    suspend fun getAllDate() : List<Date>

    suspend fun getReview(date : Int) : List<Review>

    suspend fun insertReview(review : Review)

    suspend fun deleteReview(review: Review)

    suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutine)

    fun getAllRepeatRoutineByFlow() : Flow<List<RepeatRoutine>>

    suspend fun getAllRepeatRoutine() : List<RepeatRoutine>

    suspend fun deleteRepeatRoutine(repeatRoutine: RepeatRoutine)

    suspend fun insertAllRoutine(routineList: List<Routine>)
}