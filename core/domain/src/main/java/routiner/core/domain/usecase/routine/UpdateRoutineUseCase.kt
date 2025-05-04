package routiner.core.domain.usecase.routine

import routiner.core.model.Routine
import routiner.core.domain.repository.RoutineRepository
import javax.inject.Inject

class UpdateRoutineUseCase @Inject constructor(private val repository: RoutineRepository) {

    suspend operator fun invoke(routine: Routine) {
        repository.updateRoutine(routine)
    }

}
