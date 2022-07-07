package com.hegunhee.routiner.ui.repeat

sealed class ClickEvent {
    object Uninitalized : ClickEvent()

    object Clicked : ClickEvent()

    object Finished : ClickEvent()
}