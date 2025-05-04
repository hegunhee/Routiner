package routiner.core.domain.repository

import hegunhee.routiner.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun insertCategory(category: Category)

    fun getCategoriesFlow() : Flow<List<Category>>

    suspend fun deleteCategory(category : Category) : Int

}