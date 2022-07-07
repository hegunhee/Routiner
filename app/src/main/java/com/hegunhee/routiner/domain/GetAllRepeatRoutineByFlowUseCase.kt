package com.hegunhee.routiner.domain

import com.hegunhee.routiner.data.entity.RepeatRoutine
import com.hegunhee.routiner.model.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class GetAllRepeatRoutineByFlowUseCase @Inject constructor(private val repository: Repository) : UseCase {

    operator fun invoke(): Flow<List<RepeatRoutine>> {
        return repository.getAllRepeatRoutineByFlow()
    }
}