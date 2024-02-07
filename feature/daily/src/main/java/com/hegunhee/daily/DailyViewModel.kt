package com.hegunhee.daily

import androidx.lifecycle.*
import com.example.domain.model.Routine
import com.example.domain.usecase.routine.DeleteRoutineUseCase
import com.example.domain.usecase.routine.GetAllDailyRoutineByFlowUseCase
import com.example.domain.usecase.routine.UpdateToggleRoutineUseCase
import com.hegunhee.routiner.mylibrary.getTodayDate
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
    private val getAllDailyRoutineByFlowUseCase: GetAllDailyRoutineByFlowUseCase,
    private val deleteRoutineUseCase: DeleteRoutineUseCase,
    private val updateToggleRoutineUseCase: UpdateToggleRoutineUseCase
) : ViewModel(), DailyActionHandler {

    val dailyRoutineEntityList: StateFlow<List<Routine>> = getAllDailyRoutineByFlowUseCase(
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

    private val _onClickInsertRoutineButton : MutableSharedFlow<Unit> = MutableSharedFlow()
    val onClickInsertRoutineButton : SharedFlow<Unit> = _onClickInsertRoutineButton.asSharedFlow()

    val dailyRoutineProgressText : StateFlow<String> = dailyRoutineEntityList.map {
        return@map if(it.isEmpty()){
            "0 / 0"
        }else{
            "${it.count { it.isFinished }} / ${it.size}"
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = "0 / 0"
    )

    override fun onClickRoutineInsert() {
        viewModelScope.launch{
            _onClickInsertRoutineButton.emit(Unit)
        }
    }

    override fun deleteRoutine(routineNum: Int) {
        viewModelScope.launch{
            deleteRoutineUseCase(routineNum)
        }
    }

    override fun toggleFinishRoutine(routine: Routine) {
        viewModelScope.launch {
            updateToggleRoutineUseCase(routine.copy(isFinished = !routine.isFinished))
        }
    }
}