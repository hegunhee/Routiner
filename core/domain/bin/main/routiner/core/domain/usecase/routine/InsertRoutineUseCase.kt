package routiner.core.domain.usecase.routine

import hegunhee.routiner.model.Routine
import routiner.core.domain.repository.RoutineRepository
import javax.inject.Inject

class InsertRoutineUseCase @Inject constructor(private val repository: RoutineRepository) {

    suspend operator fun invoke(routine: Routine) {
        repository.insertRoutine(routine)
    }

}
