package com.example.domain.repository

import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun insertDate(date : Int)

    suspend fun getAllDateList() : List<Date>

    suspend fun insertCategory(category: Category)

    fun getAllCategoryListByFlow() : Flow<List<Category>>

    suspend fun deleteCategory(category : Category)

    fun getSortedDayOfWeekList() : List<String>
}