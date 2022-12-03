package com.example.domain.repository

import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllDailyRoutineByFlow(date : Int) : Flow<List<Routine>>

    suspend fun insertDailyRoutine(routineEntity: Routine)

    suspend fun deleteAllRoutineByDate(date : Int)

    suspend fun deleteRoutine(id : Int)

    suspend fun getRoutineListByDate(date :Int) : List<Routine>

    suspend fun insertDate(dateEntity : Date)

    suspend fun getAllDate() : List<Date>

    suspend fun getReview(date : Int) : List<Review>

    suspend fun insertReview(reviewEntity : Review)

    suspend fun deleteReview(reviewEntity: Review)

    suspend fun insertRepeatRoutine(repeatRoutineEntity: RepeatRoutine)

    fun getAllRepeatRoutineByFlow() : Flow<List<RepeatRoutine>>

    suspend fun getAllRepeatRoutine() : List<RepeatRoutine>

    suspend fun deleteRepeatRoutine(text: String)

    suspend fun insertAllRoutine(routineEntityList: List<Routine>)

    suspend fun insertCategory(categoryEntity: Category)

    fun getAllCategoryByFlow() : Flow<List<Category>>

    suspend fun getCurrentDate() : Int

    suspend fun setCurrentDate(date : Int)

    suspend fun getDefaultCurrentDate() : Int

    fun setNotiSendValue(notiValue : Boolean)

    fun getNotiSendValue() : Boolean
}