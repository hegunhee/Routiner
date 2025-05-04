package routiner.core.domain.usecase.routine

import routiner.core.domain.repository.RoutineRepository
import javax.inject.Inject

class DeleteRoutinesByDateUseCase @Inject constructor(private val repository: RoutineRepository) {

    suspend operator fun invoke(date: Int): Int {
        return repository.deleteRoutinesByDate(date)
    }
}
