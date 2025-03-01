package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.mapper.*
import com.example.domain.model.*
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun insertCategory(category: Category) {
        localDataSource.insertCategory(category.toCategoryEntity())
    }

    override fun getAllCategoryListByFlow(): Flow<List<Category>> {
        return localDataSource.getCategoriesFlow().map { it.toCategory() }
    }

    override suspend fun deleteCategory(category: Category) {
        localDataSource.deleteCategory(category.toCategoryEntity())
    }


    override fun getSortedDayOfWeekList(): List<String> {
        return listOf("월","화","수","목","금","토","일")
    }

}
