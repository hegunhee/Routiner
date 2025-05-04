package routiner.core.domain.usecase.notification

import routiner.core.domain.repository.SettingRepository
import javax.inject.Inject

class SetAlarmNotiTimeUseCase @Inject constructor(private val repository: SettingRepository) {

    operator fun invoke(time : String){
        repository.setAlarmNotiTime(time)
    }
}