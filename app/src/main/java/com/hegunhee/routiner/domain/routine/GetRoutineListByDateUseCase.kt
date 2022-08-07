package com.hegunhee.routiner.domain.routine

import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.domain.UseCase
import com.hegunhee.routiner.model.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class GetRoutineListByDateUseCase @Inject constructor(
    private val repository: Repository
) : UseCase {

    suspend operator fun invoke(date :Int) : List<Routine>{
        return repository.getRoutineListByDate(date)
    }
}