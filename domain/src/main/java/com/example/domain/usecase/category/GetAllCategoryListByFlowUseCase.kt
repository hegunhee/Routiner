package com.example.domain.usecase.category

import com.example.domain.model.Category
import com.example.domain.repository.Repository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoryListByFlowUseCase @Inject constructor(private val repository: Repository) : UseCase {

    operator fun invoke() : Flow<List<Category>> {
        return repository.getAllCategoryByFlow()
    }
}