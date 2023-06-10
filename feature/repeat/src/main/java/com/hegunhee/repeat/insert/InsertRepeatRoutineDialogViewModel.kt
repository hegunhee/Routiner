package com.hegunhee.repeat.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.model.DayOfWeek
import com.example.domain.model.RepeatRoutine
import com.example.domain.model.Routine
import com.example.domain.usecase.category.GetAllCategoryListByFlowUseCase
import com.example.domain.usecase.date.GetAllDayOfWeekListByFlowUseCase
import com.example.domain.usecase.date.InsertDefaultDayOfWeekListUseCase
import com.example.domain.usecase.repeatRoutine.InsertRepeatRoutineUseCase
import com.example.domain.usecase.routine.InsertRoutineUseCase
import com.hegunhee.category.CategoryActionHandler
import com.hegunhee.common.dayOfWeek.DayOfWeekActionHandler
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
    private val getAllDayOfWeekListByFlowUseCase: GetAllDayOfWeekListByFlowUseCase,
    private val insertDefaultDayOfWeekListUseCase: InsertDefaultDayOfWeekListUseCase
) : ViewModel() , InsertRepeatRoutineActionHandler,CategoryActionHandler, DayOfWeekActionHandler {

    val repeatRoutineText : MutableStateFlow<String> = MutableStateFlow<String>("")

    private val _navigationActions : MutableSharedFlow<InsertRepeatRoutineNavigationAction> = MutableSharedFlow<InsertRepeatRoutineNavigationAction>()
    val navigationActions : SharedFlow<InsertRepeatRoutineNavigationAction> = _navigationActions.asSharedFlow()

    private val _selectedDayOfWeekList : MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val selectedDayOfWeekList : StateFlow<List<String>> = _selectedDayOfWeekList.asStateFlow()

    private val sortedDayOfWeekList : List<String> = listOf("월","화","수","목","금","토","일")

    private val _dayOfWeekList : Flow<List<DayOfWeek>> = getAllDayOfWeekListByFlowUseCase().combine(selectedDayOfWeekList) { dayOfWeekList, selectedDayOfWeekList ->
        if(dayOfWeekList.isEmpty()) {
            insertDefaultDayOfWeekListUseCase()
            emptyList()
        }else{
            dayOfWeekList.map {
                if(selectedDayOfWeekList.contains(it.date)) {
                    DayOfWeek(it.date, isSelected = true)
                } else {
                    it
                }
            }
        }
    }

    val dayOfWeekList : Flow<List<DayOfWeek>>
    get() = _dayOfWeekList

    private val _selectedCategory : MutableStateFlow<Category> = MutableStateFlow(Category(""))
    private val selectedCategory : StateFlow<Category> = _selectedCategory.asStateFlow()

    private val _categoryList : Flow<List<Category>> = getAllCategoryListByFlowUseCase().combine(selectedCategory) { categoryList, category ->
        if(category.name.isBlank()){
            categoryList
        }else{
            categoryList.map {
                if(it.name == category.name){
                    Category(it.name,category.isSelected)
                }else{
                    Category(it.name)
                }
            }
        }
    }
    val categoryList : Flow<List<Category>>
        get() = _categoryList

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
        viewModelScope.launch {
            if(repeatRoutineText.isBlank()){
                _toastMessage.emit(emptyRepeatRoutineMessage)
                return@launch
            }
            val sortedDayOfWeekList = sortedDayOfWeekList.filter { selectedDayOfWeekList.value.contains(it) }
            if(sortedDayOfWeekList.isEmpty()) {
                _toastMessage.emit(emptyDayOfWeekMessage)
                return@launch
            }
            val categoryText = if(selectedCategory.value.isSelected){
                selectedCategory.value.name
            }else{
                ""
            }

            val repeatRoutine = RepeatRoutine(repeatRoutineText,sortedDayOfWeekList,categoryText)
            if(getTodayDayOfWeekFormatedKorean() in sortedDayOfWeekList){
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