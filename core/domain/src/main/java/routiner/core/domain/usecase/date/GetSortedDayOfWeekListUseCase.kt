package routiner.core.domain.usecase.date

import routiner.core.domain.repository.SettingRepository
import javax.inject.Inject

class GetSortedDayOfWeekListUseCase @Inject constructor(private val repository: SettingRepository) {

    operator fun invoke() : List<String> {
        return repository.getSortedDayOfWeekList()
    }

}
