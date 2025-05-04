package routiner.core.domain.usecase.date

import routiner.core.domain.repository.DateRepository
import routiner.core.model.Date
import javax.inject.Inject

class GetRoutineExistDateListUseCase @Inject constructor(
    private val dateRepository: DateRepository,
) {

    suspend operator fun invoke() : List<Date> {
        return dateRepository.getRoutineExistDateList()
    }
}