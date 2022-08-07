package com.hegunhee.routiner.domain.repeatRoutine

import com.hegunhee.routiner.data.entity.RepeatRoutine
import com.hegunhee.routiner.domain.UseCase
import com.hegunhee.routiner.model.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class InsertRepeatRoutineUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke(repeatRoutine: RepeatRoutine){
        repository.insertRepeatRoutine(repeatRoutine)
    }
}