package com.hegunhee.routiner.ui.repeat

import androidx.lifecycle.*
import com.hegunhee.routiner.data.entity.RepeatRoutine
import com.hegunhee.routiner.domain.GetAllRepeatRoutineByFlowUseCase
import com.hegunhee.routiner.domain.InsertRepeatRoutineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepeatViewModel @Inject constructor(
    private val insertRepeatRoutineUseCase: InsertRepeatRoutineUseCase,
    private val getAllRepeatRoutineByFlowUseCase: GetAllRepeatRoutineByFlowUseCase
): ViewModel() {

    val repeatRoutineListLiveData : LiveData<List<RepeatRoutine>> = getAllRepeatRoutineByFlowUseCase().asLiveData()

    val isRepeatRoutineListEmpty : LiveData<Boolean> = Transformations.map(repeatRoutineListLiveData){
        it.isEmpty()
    }
    private var _clickEvent : MutableLiveData<ClickEvent> = MutableLiveData<ClickEvent>(ClickEvent.Uninitalized)
    val clickEvent
    get() = _clickEvent

    fun clickFloatingActionButton() {
        _clickEvent.value = ClickEvent.Clicked
    }

    fun finishClick() {
        _clickEvent.value = ClickEvent.Finished
    }

    fun insertRepeatRoutine(text : String, dayOfWeeks : List<String>)= viewModelScope.launch(Dispatchers.IO){
        insertRepeatRoutineUseCase(RepeatRoutine(text,dayOfWeeks))
    }

}