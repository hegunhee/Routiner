package com.hegunhee.routiner.domain.routine

import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.domain.UseCase
import com.hegunhee.routiner.model.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class GetAllDailyRoutineByFlowUseCase @Inject constructor(private val repository: Repository) :
    UseCase {

    operator fun invoke(date : Int) : Flow<List<Routine>>{
        return repository.getAllDailyRoutineByFlow(date)
    }
}