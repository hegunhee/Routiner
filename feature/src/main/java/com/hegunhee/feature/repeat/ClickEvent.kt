package com.hegunhee.feature.repeat

sealed class ClickEvent {
    object Uninitalized : ClickEvent()

    object Clicked : ClickEvent()

    object Finished : ClickEvent()
}