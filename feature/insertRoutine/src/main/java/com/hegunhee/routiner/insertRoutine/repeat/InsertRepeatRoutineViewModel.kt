package com.hegunhee.routiner.insertRoutine.repeat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.model.DayOfWeek
import com.example.domain.model.RepeatRoutine
import com.example.domain.model.Routine
import com.example.domain.usecase.category.GetAllCategoryListByFlowUseCase
import com.example.domain.usecase.category.InsertCategoryUseCase
import com.example.domain.usecase.category.RemoveCategoryUseCase
import com.example.domain.usecase.date.GetSortedDayOfWeekListUseCase
import com.example.domain.usecase.repeatRoutine.InsertRepeatRoutineUseCase
import com.example.domain.usecase.routine.InsertRoutineUseCase
import com.hegunhee.category.CategoryActionHandler
import com.hegunhee.common.dayOfWeek.DayOfWeekActionHandler
import com.hegunhee.routiner.util.getTodayDate
import com.hegunhee.routiner.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertRepeatRoutineViewModel @Inject constructor(
    private val getAllCategoryListByFlowUseCase: GetAllCategoryListByFlowUseCase,
    private val insertRoutineUseCase: InsertRoutineUseCase,
    private val insertRepeatRoutineUseCase: InsertRepeatRoutineUseCase,
    private val getSortedDayOfWeekListUseCase: GetSortedDayOfWeekListUseCase,
    private val removeCategoryUseCase: RemoveCategoryUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase
) : ViewModel(), CategoryActionHandler, DayOfWeekActionHandler, InsertRepeatRoutineActionHandler {

    val routineQuery : MutableStateFlow<String> = MutableStateFlow("")
    val categoryQuery : MutableStateFlow<String> = MutableStateFlow("")

    private val _selectedDayOfWeekList : MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val selectedDayOfWeekList : StateFlow<List<String>> = _selectedDayOfWeekList.asStateFlow()

    val dayOfWeekList : StateFlow<List<DayOfWeek>> = selectedDayOfWeekList.map { selectedList ->
        getSortedDayOfWeekListUseCase().map { sortedDayOfWeek ->
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

    private val _navigationActions : MutableSharedFlow<InsertRepeatRoutineNavigationAction> = MutableSharedFlow()
    val navigationActions : SharedFlow<InsertRepeatRoutineNavigationAction> = _navigationActions.asSharedFlow()

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    override fun onInsertRoutineButtonClick() {
        viewModelScope.launch {
            val routineText = routineQuery.value
            val currentDayOfWeekList = dayOfWeekList.value.filter { it.isSelected }.map { it.date }
            if(routineText.isBlank()) {
                _toastMessage.emit("입력이 비어있습니다.")
                return@launch
            }
            if(currentDayOfWeekList.isEmpty()) {
                _toastMessage.emit("날짜 정보가 비어있습니다.")
                return@launch
            }
            val categoryText = if(selectedCategory.value.isSelected){
                selectedCategory.value.name
            }else{
                ""
            }
            val repeatRoutine = RepeatRoutine(routineText,currentDayOfWeekList,categoryText)
            if(getTodayDayOfWeekFormatedKorean() in currentDayOfWeekList){
                insertRoutineUseCase(Routine(date = getTodayDate(), text = routineText, category = categoryText))
            }
            insertRepeatRoutineUseCase(repeatRoutine)
            _navigationActions.emit(InsertRepeatRoutineNavigationAction.InsertRepeatRoutine)
        }
    }


    override fun onCategoryAddClick() {
        val query = categoryQuery.value
        if(query.isBlank()) {
            return
        }
        if(categoryList.value.map(Category::name).contains(query)) {
            viewModelScope.launch {
                _toastMessage.emit("이미 존재하는 카테고리입니다.")
            }
        }
        viewModelScope.launch {
            insertCategoryUseCase(Category(name = query))
        }
    }
    override fun onCategoryRemoveClick(category: Category) {
        viewModelScope.launch {
            removeCategoryUseCase(category)
            if(selectedCategory.value.name == category.name) {
                _selectedCategory.value = Category("")
            }
        }
    }

    override fun onSelectCategory(categoryText: String, isCategorySelected: Boolean) {
        viewModelScope.launch {
            _selectedCategory.value = Category(categoryText,!isCategorySelected)
        }
    }

    override fun onClickDayOfWeek(date: String) {
        if(selectedDayOfWeekList.value.contains(date)){
            _selectedDayOfWeekList.value = selectedDayOfWeekList.value.filter { it != date }
        }else{
            _selectedDayOfWeekList.value = selectedDayOfWeekList.value + date
        }
    }
}