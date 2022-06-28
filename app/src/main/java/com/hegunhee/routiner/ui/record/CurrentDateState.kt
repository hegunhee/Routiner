package com.hegunhee.routiner.ui.record

sealed class CurrentDateState {

    object Uninitalized : CurrentDateState()

    data class SetData(val date : Int) : CurrentDateState()
}