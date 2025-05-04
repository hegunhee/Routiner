package routiner.core.domain.usecase.repeatRoutine

import routiner.core.model.RepeatRoutine
import routiner.core.domain.repository.RepeatRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepeatRoutinesFlowUseCase @Inject constructor(private val repository: RepeatRoutineRepository) {

    operator fun invoke(): Flow<List<RepeatRoutine>> {
        return repository.getRepeatRoutinesFlow()
    }

}
