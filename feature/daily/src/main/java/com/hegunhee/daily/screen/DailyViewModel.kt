package com.hegunhee.daily.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.routine.GetRoutinesFlowByDateUseCase
import com.hegunhee.daily.screen.DailyUiState.Init
import com.hegunhee.routiner.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getRoutinesByFlow: GetRoutinesFlowByDateUseCase
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

}
