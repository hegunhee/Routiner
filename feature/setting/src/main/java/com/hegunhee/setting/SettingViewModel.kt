package com.hegunhee.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import routiner.core.domain.usecase.notification.GetAlarmNotiTimeUseCase
import routiner.core.domain.usecase.notification.SetAlarmNotiTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getAlarmNotiTimeUseCase: GetAlarmNotiTimeUseCase,
    private val setAlarmNotiTimeUseCase: SetAlarmNotiTimeUseCase,
) : ViewModel() {

    private val _alarmState: MutableSharedFlow<AlarmState> = MutableSharedFlow()
    val alarmState: SharedFlow<AlarmState> = _alarmState.asSharedFlow()

    private val _alarmEnabled: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val alarmEnabled: StateFlow<Boolean> = _alarmEnabled.asStateFlow()

    private val _alarmHour: MutableStateFlow<String> = MutableStateFlow("00")
    val alarmHour: StateFlow<String> = _alarmHour.asStateFlow()

    private val _alarmMinute: MutableStateFlow<String> = MutableStateFlow("00")
    val alarmMinute: StateFlow<String> = _alarmMinute.asStateFlow()

    init {
        viewModelScope.launch {
            val currentAlarm = getAlarmNotiTimeUseCase()
            if (currentAlarm.enableAlarm) {
                _alarmEnabled.value = true
                _alarmHour.value = currentAlarm.hour
                _alarmMinute.value = currentAlarm.minute
            }
        }
    }

    fun onClickAlarmEnabled(enabled: Boolean) {
        _alarmEnabled.value = enabled

        viewModelScope.launch {
            if (!enabled) {
                _alarmState.emit(AlarmState.Cancel)
            }
        }

    }

    fun onAlarmHourChanged(newHour: String) {
        _alarmHour.value = newHour
    }

    fun onAlarmMinuteChanged(newMinute: String) {
        _alarmMinute.value = newMinute
    }

    fun onAlarmChanged(hour: String, minute: String) {
        viewModelScope.launch {
            setAlarmNotiTimeUseCase("$hour:$minute")
            _alarmState.emit(AlarmState.Register(hour.toInt(), minute.toInt()))
        }
    }

}
