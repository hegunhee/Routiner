package com.hegunhee.routiner.ui.repeat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepeatViewModel @Inject constructor(): ViewModel() {

    private var _clickEvent : MutableLiveData<ClickEvent> = MutableLiveData<ClickEvent>(ClickEvent.Uninitalized)
    val clickEvent
    get() = _clickEvent

    fun clickFloatingActionButton() {
        _clickEvent.value = ClickEvent.Clicked
    }

    fun finishClick() {
        _clickEvent.value = ClickEvent.Finished
    }

}