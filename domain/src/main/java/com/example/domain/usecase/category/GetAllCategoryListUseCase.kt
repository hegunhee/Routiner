package com.example.domain.usecase.category

import com.example.domain.model.Category
import com.example.domain.repository.Repository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class GetAllCategoryListUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke() : List<Category>{
        return repository.getAllCategory()
    }
}