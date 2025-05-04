package routiner.core.domain.usecase.date

import routiner.core.domain.repository.SettingRepository
import javax.inject.Inject

class SetCurrentDateUseCase @Inject constructor(private val repository: SettingRepository) {

    suspend operator fun invoke(date: Int) {
        repository.setCurrentDate(date)
    }

}
