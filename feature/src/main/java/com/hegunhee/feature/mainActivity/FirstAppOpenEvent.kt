package com.hegunhee.feature.mainActivity

sealed class FirstAppOpenEvent {

    object UnInitalized : FirstAppOpenEvent()

    object OpenDialog : FirstAppOpenEvent()

    object Finished : FirstAppOpenEvent()
}