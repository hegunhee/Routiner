package com.hegunhee.routiner.domain

import com.hegunhee.routiner.model.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class DeleteAllRoutineByDateUseCase @Inject constructor(private val repository: Repository ) : UseCase {

    suspend operator fun invoke(date : Int){
        repository.deleteAllRoutineByDate(date)
    }
}