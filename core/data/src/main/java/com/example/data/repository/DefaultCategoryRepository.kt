package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.mapper.toCategory
import com.example.data.mapper.toCategoryEntity
import hegunhee.routiner.model.Category
import com.example.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCategoryRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : CategoryRepository {

    override suspend fun insertCategory(category: Category) {
        localDataSource.insertCategory(category.toCategoryEntity())
    }

    override fun getCategoriesFlow(): Flow<List<Category>> {
        return localDataSource.getCategoriesFlow().map { it.toCategory() }
    }

    override suspend fun deleteCategory(category: Category): Int {
        return localDataSource.deleteCategory(category.toCategoryEntity())
    }

}
