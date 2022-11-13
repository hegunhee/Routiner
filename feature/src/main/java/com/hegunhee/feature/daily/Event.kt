package com.hegunhee.feature.daily

sealed class Event{

    object Uninitalized : Event()

    object Clicked : Event()

    object EndClick : Event()
}
