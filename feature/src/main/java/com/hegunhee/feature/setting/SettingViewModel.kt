package com.hegunhee.feature.setting

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.notification.GetNotiSendValueUseCase
import com.example.domain.usecase.notification.SetNotiSendValueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getNotiSendValueUseCase: GetNotiSendValueUseCase,
    private val setNotiSendValueUseCase: SetNotiSendValueUseCase
) : ViewModel(){


    fun getNotiSendValue() : Boolean{
        return getNotiSendValueUseCase()
    }

    fun setNotiSendValue(isActive : Boolean){
        setNotiSendValueUseCase(isActive)
    }
}