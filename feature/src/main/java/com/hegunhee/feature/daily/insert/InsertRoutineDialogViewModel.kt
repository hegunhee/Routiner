package com.hegunhee.feature.daily.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Routine
import com.example.domain.usecase.routine.GetRoutineListByDateUseCase
import com.example.domain.usecase.routine.InsertDailyRoutineUseCase
import com.example.domain.usecase.routine.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertRoutineDialogViewModel @Inject constructor(
    private val getRoutineListByDateUseCase: GetRoutineListByDateUseCase,
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase
) : ViewModel(), InsertRoutineActionHandler {

    val routineText : MutableStateFlow<String> = MutableStateFlow<String>("")

    private val _dismissDialog : MutableSharedFlow<Unit> = MutableSharedFlow<Unit>()
    val dismissDialog : SharedFlow<Unit> = _dismissDialog.asSharedFlow()

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow<String>()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    private val emptyMessage = "입력이 비어있습니다."
    private val sameRoutineMessage = "중복된 루틴입니다."

    override fun cancelRoutine() {
        viewModelScope.launch {
            _dismissDialog.emit(Unit)
        }
    }

    override fun insertRoutine() {
        viewModelScope.launch {
            val routineText = routineText.value
            if (routineText.isBlank()) {
                _toastMessage.emit(emptyMessage)
                return@launch
            }
            getRoutineListByDateUseCase(getTodayDate()).let { routineList ->
                if(routineList.map(Routine::text).contains(routineText)){
                    _toastMessage.emit(sameRoutineMessage)
                }else{
                    insertDailyRoutineUseCase(Routine(date= getTodayDate(),text = routineText))
                    _dismissDialog.emit(Unit)
                }
            }
        }
    }
}