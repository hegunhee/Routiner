package com.example.domain.usecase.category

import com.example.domain.model.Category
import com.example.domain.repository.Repository
import com.hegunhee.routiner.domain.UseCase
import javax.inject.Inject

class InsertCategoryUseCase @Inject constructor(private val repository: Repository)  : UseCase {

    suspend operator fun invoke(categoryEntity: Category){
        repository.insertCategory(categoryEntity)
    }
}