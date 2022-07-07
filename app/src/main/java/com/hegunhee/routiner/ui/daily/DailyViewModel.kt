package com.hegunhee.routiner.ui.daily

import androidx.lifecycle.*
import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.domain.*
import com.hegunhee.routiner.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getAllDailyRoutineByFlowUseCase: GetAllDailyRoutineByFlowUseCase,
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase,
    private val deleteRoutineUseCase: DeleteRoutineUseCase
) : ViewModel() {

    val dailyRoutineListLiveData: LiveData<List<Routine>> = getAllDailyRoutineByFlowUseCase(getTodayDate()).asLiveData()

    val recyclerViewVisible: LiveData<Boolean> = Transformations.map(dailyRoutineListLiveData) { dailyRoutineListLiveData.value?.isNotEmpty() }

    private var _onClickEvent : MutableLiveData<Event> = MutableLiveData(Event.Uninitalized)
    val onClickEvent : LiveData<Event>
    get() = _onClickEvent

    val dailyRoutineProgress : LiveData<String> = Transformations.map(dailyRoutineListLiveData){
        return@map if(it.isEmpty()){
            "0 / 0"
        }else{
            "${it.count { it.isFinished }} / ${it.size}"
        }
    }

    fun onClickRoutineInsert() = viewModelScope.launch{
        _onClickEvent.postValue(Event.Clicked)
    }

    fun insertRoutine(text : String) = viewModelScope.launch(Dispatchers.IO) {
        val isExistSameText : Boolean = dailyRoutineListLiveData.value?.filter { it.text == text }?.size != 0
        if(isExistSameText){

        } else{
            insertDailyRoutineUseCase(Routine(getTodayDate(),text))
        }
    }

    fun deleteData(id : Int) = viewModelScope.launch(Dispatchers.IO) {
        deleteRoutineUseCase(id)
    }

    fun toggleData(routine: Routine) = viewModelScope.launch(Dispatchers.IO) {
        insertDailyRoutineUseCase(routine)
    }

    fun endClick() {
        _onClickEvent.postValue(Event.EndClick)
    }




}