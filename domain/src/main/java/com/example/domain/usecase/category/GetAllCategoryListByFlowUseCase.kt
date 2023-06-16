package com.example.domain.usecase.category

import com.example.domain.model.Category
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoryListByFlowUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke() : Flow<List<Category>> {
        return repository.getAllCategoryListByFlow()
    }
}