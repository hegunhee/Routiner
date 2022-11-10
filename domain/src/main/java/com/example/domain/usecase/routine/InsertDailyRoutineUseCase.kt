package com.example.domain.usecase.routine

import com.example.domain.model.Routine
import com.example.domain.repository.Repository
import com.hegunhee.routiner.domain.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertDailyRoutineUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke(routineEntity: Routine){
        repository.insertDailyRoutine(routineEntity)
    }
}