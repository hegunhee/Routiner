package com.example.domain.usecase.category

import com.example.domain.model.Category
import com.example.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoryListByFlowUseCase @Inject constructor(private val repository: CategoryRepository) {

    operator fun invoke() : Flow<List<Category>> {
        return repository.getCategoriesFlow()
    }
}