package com.hegunhee.feature.repeat

import androidx.lifecycle.*
import com.example.domain.model.Category
import com.example.domain.model.RepeatRoutine
import com.example.domain.model.Routine
import com.example.domain.usecase.category.InsertCategoryUseCase
import com.example.domain.usecase.repeatRoutine.DeleteRepeatRoutineUseCase
import com.example.domain.usecase.repeatRoutine.GetAllRepeatRoutineByFlowUseCase
import com.example.domain.usecase.repeatRoutine.InsertRepeatRoutineUseCase
import com.example.domain.usecase.routine.InsertDailyRoutineUseCase
import com.hegunhee.feature.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepeatViewModel @Inject constructor(
    private val insertRepeatRoutineUseCase: InsertRepeatRoutineUseCase,
    private val getAllRepeatRoutineByFlowUseCase: GetAllRepeatRoutineByFlowUseCase,
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase,
    private val deleteRepeatRoutineUseCase: DeleteRepeatRoutineUseCase,
): ViewModel(), RepeatActionHandler {

    val repeatRoutineListLiveData : LiveData<List<RepeatRoutine>> = getAllRepeatRoutineByFlowUseCase().asLiveData()

    val isRepeatRoutineListEmpty : LiveData<Boolean> = Transformations.map(repeatRoutineListLiveData){
        it.isEmpty()
    }

    private val _navigationActions : MutableSharedFlow<RepeatNavigationAction> = MutableSharedFlow<RepeatNavigationAction>()
    val navigationActions : SharedFlow<RepeatNavigationAction> = _navigationActions.asSharedFlow()

    private var _categoryList : MutableLiveData<List<Category>> = MutableLiveData(listOf())
    val categoryList : LiveData<List<Category>>
        get() = _categoryList

    fun insertDailyRoutine(text : String,category : String = "") = viewModelScope.launch(Dispatchers.IO) {
        insertDailyRoutineUseCase(Routine(getTodayDate(),text, category = category))
    }

    fun insertRepeatRoutine(text : String, dayOfWeeks : List<String>,category : String = "")= viewModelScope.launch(Dispatchers.IO){
        insertRepeatRoutineUseCase(RepeatRoutine(text,dayOfWeeks,category = category))
    }

    fun deleteRepeatRoutine(text : String) = viewModelScope.launch(Dispatchers.IO){
        deleteRepeatRoutineUseCase(text)
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