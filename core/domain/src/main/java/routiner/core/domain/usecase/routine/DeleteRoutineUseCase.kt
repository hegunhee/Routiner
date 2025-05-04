package routiner.core.domain.usecase.routine

import routiner.core.domain.repository.RoutineRepository
import javax.inject.Inject

class DeleteRoutineUseCase @Inject constructor(private val repository: RoutineRepository) {

    suspend operator fun invoke(id: Int): Int {
        return repository.deleteRoutine(id)
    }

}
