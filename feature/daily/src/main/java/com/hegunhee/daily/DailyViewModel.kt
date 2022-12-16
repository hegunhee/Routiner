package com.hegunhee.daily

import androidx.lifecycle.*
import com.example.domain.model.Routine
import com.example.domain.usecase.routine.DeleteRoutineUseCase
import com.example.domain.usecase.routine.GetAllDailyRoutineByFlowUseCase
import com.example.domain.usecase.routine.InsertDailyRoutineUseCase
import com.hegunhee.common.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getAllDailyRoutineByFlowUseCase: GetAllDailyRoutineByFlowUseCase,
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase,
    private val deleteRoutineUseCase: DeleteRoutineUseCase,
) : ViewModel(), DailyActionHandler {

    val dailyRoutineEntityListLiveData: LiveData<List<Routine>> = getAllDailyRoutineByFlowUseCase(
        getTodayDate()
    ).asLiveData()

    val recyclerViewVisible: LiveData<Boolean> = Transformations.map(dailyRoutineEntityListLiveData) { dailyRoutineEntityListLiveData.value?.isNotEmpty() }

    private var _onClickEvent : MutableSharedFlow<Boolean> = MutableSharedFlow()
    val onClickEvent : MutableSharedFlow<Boolean>
    get() = _onClickEvent

    val dailyRoutineProgress : LiveData<String> = Transformations.map(dailyRoutineEntityListLiveData){
        return@map if(it.isEmpty()){
            "0 / 0"
        }else{
            "${it.count { it.isFinished }} / ${it.size}"
        }
    }

    override fun onClickRoutineInsert() {
        viewModelScope.launch{
            _onClickEvent.emit(true)
        }
    }

    override fun deleteRoutine(routineNum: Int) {
        viewModelScope.launch{
            deleteRoutineUseCase(routineNum)
        }
    }

    override fun toggleFinishRoutine(routine: Routine) {
        viewModelScope.launch {
            insertDailyRoutineUseCase(routine.copy(isFinished = !routine.isFinished))
        }
    }
}