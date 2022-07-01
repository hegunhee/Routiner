package com.hegunhee.routiner.ui.mainActivity

sealed class FirstAppOpenEvent {

    object UnInitalized : FirstAppOpenEvent()

    object OpenDialog : FirstAppOpenEvent()

    object Finished : FirstAppOpenEvent()
}