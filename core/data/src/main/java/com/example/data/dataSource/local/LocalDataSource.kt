package com.example.data.dataSource.local

import com.example.data.entity.*
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertRoutine(routineEntity: RoutineEntity)

    suspend fun insertRoutines(routineList: List<RoutineEntity>) : List<Long>

    fun getRoutinesFlowByDate(date : Int) : Flow<List<RoutineEntity>>

    suspend fun getRoutinesByDate(date :Int) : List<RoutineEntity>

    suspend fun deleteRoutinesByDate(date : Int) : Int

    suspend fun deleteRoutine(id : Int) : Int

    suspend fun updateRoutine(routineEntity: RoutineEntity)


    suspend fun insertDate(date : DateEntity)

    suspend fun getAllDateList() : List<DateEntity>

    suspend fun getReviewOrNullByDate(date : Int) : ReviewEntity?

    suspend fun insertReview(review : ReviewEntity)

    suspend fun deleteReview(review: ReviewEntity)

    suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutineEntity)

    fun getAllRepeatRoutineListByFlow() : Flow<List<RepeatRoutineEntity>>

    suspend fun getAllRepeatRoutineList() : List<RepeatRoutineEntity>

    suspend fun deleteRepeatRoutine(text: String)

    suspend fun insertCategory(category: CategoryEntity)

    fun getAllCategoryListByFlow() : Flow<List<CategoryEntity>>

    suspend fun deleteCategory(categoryEntity : CategoryEntity)

    suspend fun getCurrentDate() : Int

    suspend fun setCurrentDate(date : Int)

    fun setAlarmNotiTime(time : String)

    fun getAlarmNotiTime() : String

    fun isAppFirstOpened() : Boolean

    fun setAppFirstOpened()
}
