package com.example.domain.usecase.category

import com.example.domain.model.Category
import com.example.domain.repository.Repository
import javax.inject.Inject

class DeleteCategoryUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(category : Category) {
        repository.deleteCategory(category)
    }
}