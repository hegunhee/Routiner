package com.hegunhee.routiner.domain.category

import com.hegunhee.routiner.data.entity.Category
import com.hegunhee.routiner.domain.UseCase
import com.hegunhee.routiner.model.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class InsertCategoryUseCase @Inject constructor(private val repository: Repository)  : UseCase {

    suspend operator fun invoke(category: Category){
        repository.insertCategory(category)
    }
}