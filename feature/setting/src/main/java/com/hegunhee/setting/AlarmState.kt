package com.hegunhee.setting

sealed interface AlarmState {

    data object Cancel : AlarmState

    data class Register(
        val hour : Int,
        val minute : Int,
    ) : AlarmState

}
