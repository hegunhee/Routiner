package com.hegunhee.routiner.domain

import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.model.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class GetAllDailyRoutineUseCase @Inject constructor(private val repository: Repository) : UseCase {

    operator fun invoke(date : Int) : Flow<List<Routine>>{
        return repository.getAllDailyRoutine(date)
    }
}