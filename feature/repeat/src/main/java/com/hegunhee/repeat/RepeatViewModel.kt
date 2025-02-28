package com.hegunhee.repeat

import androidx.lifecycle.*
import hegunhee.routiner.model.RepeatRoutine
import com.example.domain.usecase.repeatRoutine.DeleteRepeatRoutineUseCase
import com.example.domain.usecase.repeatRoutine.GetRepeatRoutinesFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepeatViewModel @Inject constructor(
    private val getRepeatRoutinesFlowUseCase: GetRepeatRoutinesFlowUseCase,
    private val deleteRepeatRoutineUseCase: DeleteRepeatRoutineUseCase,
): ViewModel(), RepeatActionHandler {

    val repeatRoutineList : StateFlow<List<RepeatRoutine>> = getRepeatRoutinesFlowUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    val isRepeatRoutineListEmpty : StateFlow<Boolean> = repeatRoutineList.map {
        it.isEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )

    private val _navigationActions : MutableSharedFlow<RepeatNavigationAction> = MutableSharedFlow<RepeatNavigationAction>()
    val navigationActions : SharedFlow<RepeatNavigationAction> = _navigationActions.asSharedFlow()

    override fun deleteRepeatRoutine(text : String) {
        viewModelScope.launch {
            deleteRepeatRoutineUseCase(text)
        }
    }

    override fun openInsertRepeatRoutineDialog() {
        viewModelScope.launch {
            _navigationActions.emit(RepeatNavigationAction.InsertRepeatRoutine)
        }
    }
}