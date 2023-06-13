package com.example.data.dataSource.local

import com.example.data.entity.*
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllDailyRoutineByFlow(date : Int) : Flow<List<RoutineEntity>>

    suspend fun insertRoutine(routineEntity: RoutineEntity)

    suspend fun deleteAllRoutineByDate(date : Int)

    suspend fun deleteRoutine(id : Int)

    suspend fun updateRoutine(routineEntity: RoutineEntity)

    suspend fun getRoutineListByDate(date :Int) : List<RoutineEntity>

    suspend fun insertDate(date : DateEntity)

    suspend fun getAllDateList() : List<DateEntity>

    suspend fun getReviewOrNullByDate(date : Int) : ReviewEntity?

    suspend fun insertReview(review : ReviewEntity)

    suspend fun deleteReview(review: ReviewEntity)

    suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutineEntity)

    fun getAllRepeatRoutineListByFlow() : Flow<List<RepeatRoutineEntity>>

    suspend fun getAllRepeatRoutineList() : List<RepeatRoutineEntity>

    suspend fun deleteRepeatRoutine(text: String)

    suspend fun insertAllRoutine(routineList: List<RoutineEntity>)

    suspend fun insertCategory(category: CategoryEntity)

    fun getAllCategoryListByFlow() : Flow<List<CategoryEntity>>

    suspend fun getCurrentDate() : Int

    suspend fun setCurrentDate(date : Int)

    fun setNotiSendValue(notiValue : Boolean)

    fun getNotiSendValue() : Boolean

    fun isAppFirstOpened() : Boolean

    fun setAppFirstOpened()

    fun getAllDayOfWeekListByFlow() : Flow<List<DayOfWeekEntity>>

    suspend fun insertDefaultDayOfWeekList(dayOfWeekList : List<DayOfWeekEntity>)
}