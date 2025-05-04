package routiner.core.domain.usecase.date

import routiner.core.domain.repository.SettingRepository
import javax.inject.Inject


class IsAppFirstOpenUseCase @Inject constructor(private val repository: SettingRepository) {

    operator fun invoke(): Boolean {
        return repository.isAppFirstOpened()
    }

}
