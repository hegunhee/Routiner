package routiner.core.domain.usecase.routine

import routiner.core.model.Routine
import routiner.core.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoutinesFlowByDateUseCase @Inject constructor(private val repository: RoutineRepository) {

    operator fun invoke(date: Int): Flow<List<Routine>> {
        return repository.getRoutinesFlowByDate(date)
    }

}
