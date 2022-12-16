package com.hegunhee.daily.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.model.Routine
import com.example.domain.usecase.category.GetAllCategoryListByFlowUseCase
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
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase,
    private val getAllCategoryListByFlowUseCase: GetAllCategoryListByFlowUseCase
) : ViewModel(), InsertRoutineActionHandler {

    val routineText : MutableStateFlow<String> = MutableStateFlow<String>("")

    private val _navigateActions = MutableSharedFlow<InsertRoutineNavigationAction>()
    val navigateActions : SharedFlow<InsertRoutineNavigationAction> = _navigateActions.asSharedFlow()

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow<String>()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    private val _categoryList : Flow<List<Category>> = getAllCategoryListByFlowUseCase()
    val categoryList : Flow<List<Category>>
    get() = _categoryList

    var categoryText : String = ""

    private val emptyMessage = "입력이 비어있습니다."
    private val sameRoutineMessage = "중복된 루틴입니다."

    override fun cancelRoutine() {
        viewModelScope.launch {
            _navigateActions.emit(InsertRoutineNavigationAction.DismissDialog)
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
                    insertDailyRoutineUseCase(Routine(date= getTodayDate(),text = routineText, category = categoryText))
                    _navigateActions.emit(InsertRoutineNavigationAction.DismissDialog)
                }
            }
        }
    }

    override fun openInsertCategoryDialog() {
        viewModelScope.launch {
            _navigateActions.emit(InsertRoutineNavigationAction.InsertCategoryDialog)
        }
    }
}