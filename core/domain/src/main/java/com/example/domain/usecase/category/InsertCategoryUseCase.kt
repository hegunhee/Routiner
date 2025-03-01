package com.example.domain.usecase.category

import hegunhee.routiner.model.Category
import com.example.domain.repository.CategoryRepository
import javax.inject.Inject

class InsertCategoryUseCase @Inject constructor(private val repository: CategoryRepository) {

    suspend operator fun invoke(category: Category){
        repository.insertCategory(category)
    }
}