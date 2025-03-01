package com.hegunhee.daily

import androidx.lifecycle.*
import hegunhee.routiner.model.Routine
import com.example.domain.usecase.routine.DeleteRoutineUseCase
import com.example.domain.usecase.routine.GetRoutinesFlowByDateUseCase
import com.example.domain.usecase.routine.UpdateRoutineUseCase
import com.hegunhee.routiner.util.getTodayDate
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
class DailyViewModel @Inject constructor(
    private val getRoutinesFlowByDateUseCase: GetRoutinesFlowByDateUseCase,
    private val deleteRoutineUseCase: DeleteRoutineUseCase,
    private val updateRoutineUseCase: UpdateRoutineUseCase
) : ViewModel(), DailyActionHandler {

    val dailyRoutineEntityList: StateFlow<List<Routine>> = getRoutinesFlowByDateUseCase(
        getTodayDate()
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = emptyList()
    )

    val isDailyRoutineEmpty: StateFlow<Boolean> = dailyRoutineEntityList.map{
        dailyRoutineEntityList.value.isEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = true
    )

    private val _navigationActions : MutableSharedFlow<DailyNavigationAction> = MutableSharedFlow()
    val navigationActions : SharedFlow<DailyNavigationAction> = _navigationActions.asSharedFlow()

    override fun onClickRoutineInsert() {
        viewModelScope.launch{
            _navigationActions.emit(DailyNavigationAction.InsertRoutine)
        }
    }

    override fun deleteRoutine(routineNum: Int) {
        viewModelScope.launch{
            deleteRoutineUseCase(routineNum)
        }
    }

    override fun toggleFinishRoutine(routine: Routine) {
        viewModelScope.launch {
            updateRoutineUseCase(routine.copy(isFinished = !routine.isFinished))
        }
    }
}