package com.hegunhee.routiner.ui.daily

sealed class Event{

    object Uninitalized : Event()

    object Clicked : Event()

    object EndClick : Event()
}
