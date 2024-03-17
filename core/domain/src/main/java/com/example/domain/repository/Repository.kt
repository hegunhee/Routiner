package com.example.domain.repository

import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllDailyRoutineByFlow(date : Int) : Flow<List<Routine>>

    suspend fun insertRoutine(routine: Routine)

    suspend fun deleteAllRoutineByDate(date : Int)

    suspend fun deleteRoutine(id : Int)

    suspend fun updateRoutine(routine : Routine)

    suspend fun getRoutineListByDate(date :Int) : List<Routine>

    suspend fun insertDate(date : Int)

    suspend fun getAllDateList() : List<Date>

    suspend fun getReviewOrNullByDate(date : Int) : Review?

    suspend fun insertReview(review : Review)

    suspend fun deleteReview(review: Review)

    suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutine)

    fun getAllRepeatRoutineListByFlow() : Flow<List<RepeatRoutine>>

    suspend fun getAllRepeatRoutineList() : List<RepeatRoutine>

    suspend fun deleteRepeatRoutine(text: String)

    suspend fun insertAllRoutine(routineList: List<Routine>)

    suspend fun insertAllDailyRoutineFromRepeatRoutine(dayOfWeek : String)

    suspend fun insertCategory(category: Category)

    fun getAllCategoryListByFlow() : Flow<List<Category>>

    suspend fun removeCategory(category : Category)

    suspend fun getCurrentDate() : Int

    suspend fun setCurrentDate(date : Int)

    fun setNotiSendValue(notiValue : Boolean)

    fun getNotiSendValue() : Boolean

    fun isAppFirstOpened() : Boolean

    fun setAppFirstOpened()

    fun getSortedDayOfWeekList() : List<String>
}