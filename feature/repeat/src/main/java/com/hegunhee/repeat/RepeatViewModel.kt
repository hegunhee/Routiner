package com.hegunhee.repeat

import androidx.lifecycle.*
import com.example.domain.model.RepeatRoutine
import com.example.domain.usecase.repeatRoutine.DeleteRepeatRoutineUseCase
import com.example.domain.usecase.repeatRoutine.GetAllRepeatRoutineByFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepeatViewModel @Inject constructor(
    private val getAllRepeatRoutineByFlowUseCase: GetAllRepeatRoutineByFlowUseCase,
    private val deleteRepeatRoutineUseCase: DeleteRepeatRoutineUseCase,
): ViewModel(), RepeatActionHandler {

    val repeatRoutineListLiveData : LiveData<List<RepeatRoutine>> = getAllRepeatRoutineByFlowUseCase().asLiveData()

    val isRepeatRoutineListEmpty : LiveData<Boolean> = Transformations.map(repeatRoutineListLiveData){
        it.isEmpty()
    }

    private val _navigationActions : MutableSharedFlow<RepeatNavigationAction> = MutableSharedFlow<RepeatNavigationAction>()
    val navigationActions : SharedFlow<RepeatNavigationAction> = _navigationActions.asSharedFlow()

    fun deleteRepeatRoutine(text : String) {
        viewModelScope.launch {
            deleteRepeatRoutineUseCase(text)
        }
    }

    override fun openInsertRepeatRoutineDialog() {
        viewModelScope.launch {
            _navigationActions.emit(RepeatNavigationAction.InsertRepeatRoutine)
        }
    }

    override fun clickRepeatRoutine(repeatRoutine: RepeatRoutine) {
        viewModelScope.launch {
            _navigationActions.emit(RepeatNavigationAction.ClickRepeatRoutine(repeatRoutine = repeatRoutine))
        }
    }
}