package routiner.core.domain.usecase.date

import routiner.core.model.Date
import routiner.core.domain.repository.DateRepository
import javax.inject.Inject


class GetDateListUseCase @Inject constructor(private val repository: DateRepository) {

    suspend operator fun invoke(): List<Date> {
        return repository.getDateList()
    }

}
