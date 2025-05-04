package routiner.core.data.repository

import routiner.core.data.datasource.local.LocalDataSource
import routiner.core.data.mapper.toCategory
import routiner.core.data.mapper.toCategoryEntity
import routiner.core.model.Category
import routiner.core.domain.repository.CategoryRepository
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
