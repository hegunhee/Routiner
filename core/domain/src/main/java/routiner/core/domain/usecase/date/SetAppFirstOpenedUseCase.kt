package routiner.core.domain.usecase.date

import routiner.core.domain.repository.SettingRepository
import javax.inject.Inject

class SetAppFirstOpenedUseCase @Inject constructor(private val repository: SettingRepository) {

    operator fun invoke() {
        repository.setAppFirstOpened()
    }

}
