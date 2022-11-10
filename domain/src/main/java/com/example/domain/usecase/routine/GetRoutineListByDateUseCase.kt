package com.example.domain.usecase.routine

import com.example.domain.model.Routine
import com.example.domain.repository.Repository
import com.hegunhee.routiner.domain.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
class GetRoutineListByDateUseCase @Inject constructor(
    private val repository: Repository
) : UseCase {

    suspend operator fun invoke(date :Int) : List<Routine>{
        return repository.getRoutineListByDate(date)
    }
}