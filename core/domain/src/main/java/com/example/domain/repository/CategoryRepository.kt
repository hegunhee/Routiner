package com.example.domain.repository

import com.example.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun insertCategory(category: Category)

    fun getCategoriesFlow() : Flow<List<Category>>

    suspend fun deleteCategory(category : Category) : Int

}