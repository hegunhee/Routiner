package com.example.domain.usecase.category

import com.example.domain.model.Category
import com.example.domain.repository.Repository
import javax.inject.Inject

class RemoveCategoryUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(category : Category) {
        repository.removeCategory(category)
    }
}