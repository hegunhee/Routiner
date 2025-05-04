package routiner.core.domain.usecase.notification

import routiner.core.model.AlarmTime
import routiner.core.domain.repository.SettingRepository
import javax.inject.Inject

class GetAlarmNotiTimeUseCase @Inject constructor(private val repository: SettingRepository) {

    operator fun invoke() : AlarmTime {
        return repository.getAlarmNotiTime()
    }
}