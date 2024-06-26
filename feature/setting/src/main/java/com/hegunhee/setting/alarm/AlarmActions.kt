package com.hegunhee.setting.alarm

sealed class AlarmActions {

    data class Register(val hour : String,val minute : String) : AlarmActions()

    object Cancel : AlarmActions()
}