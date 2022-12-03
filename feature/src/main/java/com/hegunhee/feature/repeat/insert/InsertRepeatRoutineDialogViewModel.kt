package com.hegunhee.feature.repeat.insert

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.model.RepeatRoutine
import com.example.domain.model.Routine
import com.example.domain.usecase.category.GetAllCategoryListByFlowUseCase
import com.example.domain.usecase.repeatRoutine.InsertRepeatRoutineUseCase
import com.example.domain.usecase.routine.InsertDailyRoutineUseCase
import com.hegunhee.feature.util.getTodayDate
import com.hegunhee.feature.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertRepeatRoutineDialogViewModel @Inject constructor(
    private val allCategoryListByFlowUseCase: GetAllCategoryListByFlowUseCase,
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase,
    private val insertRepeatRoutineUseCase: InsertRepeatRoutineUseCase
) : ViewModel() , InsertRepeatRoutineActionHandler{

    val repeatRoutineText : MutableStateFlow<String> = MutableStateFlow<String>("")

    var categoryText : String = ""

    var dayOfWeekList : List<String> = emptyList()

    private val _categoryList : Flow<List<Category>> = allCategoryListByFlowUseCase()
    val categoryList : Flow<List<Category>>
    get() = _categoryList

    private val _navigationActions : MutableSharedFlow<InsertRepeatRoutineNavigationAction> = MutableSharedFlow<InsertRepeatRoutineNavigationAction>()
    val navigationActions : SharedFlow<InsertRepeatRoutineNavigationAction> = _navigationActions.asSharedFlow()

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow<String>()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    private val emptyRepeatRoutineMessage = "입력이 비어있습니다."
    private val emptyDayOfWeekMessage = "날짜 정보가 비어있습니다."

    override fun cancelRepeatRoutine() {
        viewModelScope.launch {
            _navigationActions.emit(InsertRepeatRoutineNavigationAction.DisMissDialog)
        }
    }

    override fun successRepeatRoutine() {
        val repeatRoutineText = repeatRoutineText.value
        viewModelScope.launch {
            if(repeatRoutineText.isBlank()){
                _toastMessage.emit(emptyRepeatRoutineMessage)
                return@launch
            }
            if(dayOfWeekList.isEmpty()) {
                _toastMessage.emit(emptyDayOfWeekMessage)
                return@launch
            }
            val repeatRoutine = RepeatRoutine(repeatRoutineText,dayOfWeekList,categoryText)
            if(getTodayDayOfWeekFormatedKorean() in dayOfWeekList){
                insertDailyRoutineUseCase(Routine(date = getTodayDate(), text = repeatRoutineText, category = categoryText))
            }
            insertRepeatRoutineUseCase(repeatRoutine)
            _navigationActions.emit(InsertRepeatRoutineNavigationAction.DisMissDialog)
        }

    }

    override fun openInsertCategoryDialog() {
        viewModelScope.launch {
            _navigationActions.emit(InsertRepeatRoutineNavigationAction.OpenInsertCategoryDialog)
        }
    }


}