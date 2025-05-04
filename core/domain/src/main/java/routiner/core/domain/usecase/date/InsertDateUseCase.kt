package routiner.core.domain.usecase.date

import routiner.core.domain.repository.DateRepository
import javax.inject.Inject

class InsertDateUseCase @Inject constructor(private val repository: DateRepository) {

    suspend operator fun invoke(date: Int) {
        repository.insertDate(date)
    }

}
