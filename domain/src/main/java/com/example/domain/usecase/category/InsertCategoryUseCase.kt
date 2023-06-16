package com.example.domain.usecase.category

import com.example.domain.model.Category
import com.example.domain.repository.Repository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class InsertCategoryUseCase @Inject constructor(private val repository: Repository)  : UseCase {

    suspend operator fun invoke(category: Category){
        repository.insertCategory(category)
    }
}