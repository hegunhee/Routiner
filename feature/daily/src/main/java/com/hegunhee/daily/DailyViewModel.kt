package com.hegunhee.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import routiner.core.domain.usecase.routine.DeleteRoutineUseCase
import routiner.core.domain.usecase.routine.GetRoutinesFlowByDateUseCase
import routiner.core.domain.usecase.routine.UpdateRoutineUseCase
import com.hegunhee.daily.DailyUiState.Init
import com.hegunhee.routiner.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import hegunhee.routiner.model.Routine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getRoutinesByFlow: GetRoutinesFlowByDateUseCase,
    private val updateRoutineUseCase: UpdateRoutineUseCase,
    private val deleteRoutineUseCase: DeleteRoutineUseCase,
) : ViewModel() {

    private val routines = getRoutinesByFlow(getTodayDate())

    val uiState : StateFlow<DailyUiState> = routines.map {
        if(it.isEmpty()) {
            DailyUiState.Empty
        } else {
            DailyUiState.Items(it)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = Init
    )

    fun onClickDailyRoutine(routine: Routine) {
        viewModelScope.launch {
            updateRoutineUseCase(routine.copy(isFinished = !routine.isFinished))
        }
    }

    fun onClickDeleteDailyRoutine(routineId: Int) {
        viewModelScope.launch {
            deleteRoutineUseCase(routineId)
        }
    }

}
