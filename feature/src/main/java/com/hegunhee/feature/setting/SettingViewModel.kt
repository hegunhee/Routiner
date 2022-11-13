package com.hegunhee.feature.setting

import androidx.lifecycle.ViewModel
import com.example.data.db.SharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val sharedPreferenceManager: SharedPreferenceManager) : ViewModel(){


    fun getNotiSendValue() : Boolean{
        return sharedPreferenceManager.getNotiSendValue()
    }

    fun setNotiSendValue(isActive : Boolean){
        sharedPreferenceManager.setNofiSendValue(isActive)
    }
}