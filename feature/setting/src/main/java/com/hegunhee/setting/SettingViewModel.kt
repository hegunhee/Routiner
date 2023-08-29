package com.hegunhee.setting

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.notification.GetNotiSendValueUseCase
import com.example.domain.usecase.notification.SetNotiSendValueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getNotiSendValueUseCase: GetNotiSendValueUseCase,
    private val setNotiSendValueUseCase: SetNotiSendValueUseCase
) : ViewModel(){

    val notiSendValue : MutableStateFlow<Boolean> = MutableStateFlow(getNotiSendValueUseCase())

    fun onNotiCheckedChanged(checked : Boolean) {
        notiSendValue.value = checked
        setNotiSendValue(checked)
    }
    fun setNotiSendValue(isActive : Boolean){
        setNotiSendValueUseCase(isActive)
    }
}