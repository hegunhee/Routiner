package com.hegunhee.routiner.domain

import android.util.Log
import com.hegunhee.routiner.data.entity.Category
import com.hegunhee.routiner.model.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class GetAllCategoryListUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke() : List<Category>{
        return repository.getAllCategory()
    }
}