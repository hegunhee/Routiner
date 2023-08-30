package com.hegunhee.repeat.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.model.DayOfWeek
import com.example.domain.model.RepeatRoutine
import com.example.domain.model.Routine
import com.example.domain.usecase.category.GetAllCategoryListByFlowUseCase
import com.example.domain.usecase.repeatRoutine.InsertRepeatRoutineUseCase
import com.example.domain.usecase.routine.InsertRoutineUseCase
import com.hegunhee.category.CategoryActionHandler
import com.hegunhee.common.dayOfWeek.DayOfWeekActionHandler
import com.hegunhee.common.dayOfWeek.DayOfWeekUtil
import com.hegunhee.common.util.getTodayDate
import com.hegunhee.common.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertRepeatRoutineDialogViewModel @Inject constructor(
    private val getAllCategoryListByFlowUseCase: GetAllCategoryListByFlowUseCase,
    private val insertRoutineUseCase: InsertRoutineUseCase,
    private val insertRepeatRoutineUseCase: InsertRepeatRoutineUseCase,
) : ViewModel() , InsertRepeatRoutineActionHandler,CategoryActionHandler, DayOfWeekActionHandler {

    val repeatRoutineText : MutableStateFlow<String> = MutableStateFlow<String>("")

    private val _navigationActions : MutableSharedFlow<InsertRepeatRoutineNavigationAction> = MutableSharedFlow<InsertRepeatRoutineNavigationAction>()
    val navigationActions : SharedFlow<InsertRepeatRoutineNavigationAction> = _navigationActions.asSharedFlow()

    private val _selectedDayOfWeekList : MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val selectedDayOfWeekList : StateFlow<List<String>> = _selectedDayOfWeekList.asStateFlow()

    val dayOfWeekList : StateFlow<List<DayOfWeek>> = selectedDayOfWeekList.map { selectedList ->
        DayOfWeekUtil.sortedDayOfWeekList.map { sortedDayOfWeek ->
            DayOfWeek(sortedDayOfWeek,selectedList.contains(sortedDayOfWeek))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    private val _selectedCategory : MutableStateFlow<Category> = MutableStateFlow(Category(""))
    private val selectedCategory : StateFlow<Category> = _selectedCategory.asStateFlow()

    val categoryList : StateFlow<List<Category>> = getAllCategoryListByFlowUseCase().combine(selectedCategory) { categoryList, selectedCategory ->
        if(selectedCategory.name.isBlank()){
            categoryList
        }else{
            categoryList.map {
                if(it.name == selectedCategory.name){
                    Category(it.name,selectedCategory.isSelected)
                }else{
                    Category(it.name)
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow<String>()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    private val emptyRepeatRoutineMessage = "입력이 비어있습니다."
    private val emptyDayOfWeekMessage = "날짜 정보가 비어있습니다."

    override fun cancelRepeatRoutine() {
        viewModelScope.launch {
            _navigationActions.emit(InsertRepeatRoutineNavigationAction.DisMissDialog)
        }
    }

    override fun insertRepeatRoutine() {
        val repeatRoutineText = repeatRoutineText.value
        val currentDayOfWeekList = dayOfWeekList.value.filter { it.isSelected }.map { it.date }
        viewModelScope.launch {
            if(repeatRoutineText.isBlank()){
                _toastMessage.emit(emptyRepeatRoutineMessage)
                return@launch
            }
            if(currentDayOfWeekList.isEmpty()) {
                _toastMessage.emit(emptyDayOfWeekMessage)
                return@launch
            }
            val categoryText = if(selectedCategory.value.isSelected){
                selectedCategory.value.name
            }else{
                ""
            }
            val repeatRoutine = RepeatRoutine(repeatRoutineText,currentDayOfWeekList,categoryText)
            if(getTodayDayOfWeekFormatedKorean() in currentDayOfWeekList){
                insertRoutineUseCase(Routine(date = getTodayDate(), text = repeatRoutineText, category = categoryText))
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

    override fun onClickCategory(categoryText: String, isCategorySelected: Boolean) {
        viewModelScope.launch {
            _selectedCategory.value = Category(categoryText,!isCategorySelected)
        }
    }

    override fun onClickDayOfWeek(date : String) {
        if(selectedDayOfWeekList.value.contains(date)){
            _selectedDayOfWeekList.value = selectedDayOfWeekList.value.filter { it != date }
        }else{
            _selectedDayOfWeekList.value = selectedDayOfWeekList.value + date
        }
    }
}