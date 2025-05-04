package routiner.core.domain.usecase.repeatRoutine

import routiner.core.model.RepeatRoutine
import routiner.core.domain.repository.RepeatRoutineRepository
import javax.inject.Inject

class GetAllRepeatRoutineListUseCase @Inject constructor(private val repository: RepeatRoutineRepository) {

    suspend operator fun invoke() : List<RepeatRoutine>{
        return repository.getRepeatRoutines()
    }
}