package com.example.main.guide

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(): ViewModel() {

    val isAllowNotification : MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)


    fun onClickDismissDialogButton() {
        Log.d("TEST!!!",isAllowNotification.value.toString())
    }
}