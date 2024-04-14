package com.hegunhee.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AlarmTime
import com.example.domain.usecase.notification.GetAlarmNotiTimeUseCase
import com.example.domain.usecase.notification.SetAlarmNotiTimeUseCase
import com.hegunhee.setting.alarm.AlarmActions
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
    private val setAlarmNotiTimeUseCase: SetAlarmNotiTimeUseCase
) : ViewModel() {

    private val _alarmTime: MutableStateFlow<AlarmTime> = MutableStateFlow(getAlarmNotiTimeUseCase())
    val alarmTime: StateFlow<AlarmTime> = _alarmTime.asStateFlow()

    private val _alarmActions : MutableSharedFlow<AlarmActions> = MutableSharedFlow()
    val alarmActions : SharedFlow<AlarmActions> = _alarmActions.asSharedFlow()

    fun onEnableAlarmSwitchClick(changed: Boolean) {
        if(!changed) {
            setAlarmNotiTimeUseCase(AlarmTime.DEFAULT.toTimeStamp())
            viewModelScope.launch {
                _alarmActions.emit(AlarmActions.Cancel)
            }
        }
        _alarmTime.value = alarmTime.value.copy(enableAlarm = changed)
    }

    fun setAlarmHour(hour : String) {
        _alarmTime.value = alarmTime.value.copy(hour = hour)
    }

    fun setAlarmMinute(minute : String) {
        _alarmTime.value = alarmTime.value.copy(minute = minute)
    }
    fun onSaveAlarmClick() {
        if(alarmTime.value.enableAlarm) {
            setAlarmNotiTimeUseCase(alarmTime.value.toTimeStamp())
            viewModelScope.launch {
                _alarmActions.emit(AlarmActions.Register(alarmTime.value.hour,alarmTime.value.minute))
            }
        }
    }

}