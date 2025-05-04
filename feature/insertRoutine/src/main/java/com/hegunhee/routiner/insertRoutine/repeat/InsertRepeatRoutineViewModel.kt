package com.hegunhee.routiner.insertRoutine.repeat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import routiner.core.domain.usecase.category.DeleteCategoryUseCase
import routiner.core.domain.usecase.category.GetCategoriesFlowUseCase
import routiner.core.domain.usecase.category.InsertCategoryUseCase
import routiner.core.domain.usecase.date.GetSortedDayOfWeekListUseCase
import routiner.core.domain.usecase.repeatRoutine.InsertRepeatRoutineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import hegunhee.routiner.model.Category
import hegunhee.routiner.model.DayOfWeek
import hegunhee.routiner.model.RepeatRoutine
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertRepeatRoutineViewModel @Inject constructor(
    private val getCategoriesFlowUseCase: GetCategoriesFlowUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase,
    private val getSortedDayOfWeekListUseCase: GetSortedDayOfWeekListUseCase,
    private val insertRepeatRoutineUseCase: InsertRepeatRoutineUseCase,
) : ViewModel() {

    private val categories = getCategoriesFlowUseCase()

    private val selectedCategory: MutableStateFlow<String?> = MutableStateFlow(null)

    private val _dayOfWeek: MutableStateFlow<List<DayOfWeek>> =
        MutableStateFlow(getSortedDayOfWeekListUseCase().map {
            DayOfWeek(date = it, isSelected = false)
        })
    val dayOfWeek: StateFlow<List<DayOfWeek>> = _dayOfWeek.asStateFlow()

    val uiState: StateFlow<InsertRepeatRoutineUiState> =
        categories.combine(selectedCategory) { categories, selectedCategory ->
            if (selectedCategory == null) {
                InsertRepeatRoutineUiState.Categories(categories, listOf())
            } else {
                InsertRepeatRoutineUiState.Categories(categories.map { category ->
                    if (category.name == selectedCategory) category.copy(isSelected = true)
                    else category
                }, listOf())
            }
        }.combine(dayOfWeek) { categories, dayOfWeek ->
            InsertRepeatRoutineUiState.Categories(categories.categories, dayOfWeek)
        }.stateIn(
            scope = viewModelScope,
            initialValue = InsertRepeatRoutineUiState.Init,
            started = SharingStarted.WhileSubscribed(5_000)
        )

    private val _uiEvent : MutableSharedFlow<InsertRepeatRoutineUiEvent> = MutableSharedFlow()
    val uiEvent : SharedFlow<InsertRepeatRoutineUiEvent> = _uiEvent.asSharedFlow()

    fun onClickCategory(categoryName: String) {
        if (categoryName == selectedCategory.value) {
            selectedCategory.value = null
        } else {
            selectedCategory.value = categoryName
        }
    }

    fun onClickCategoryDelete(categoryName: String) {
        if (categoryName == selectedCategory.value) {
            selectedCategory.value = null
        }
        viewModelScope.launch {
            deleteCategoryUseCase(Category(categoryName))
        }
    }

    fun onClickCategoryInsert(insertCategoryText : String) {
        viewModelScope.launch {
            if(insertCategoryText.isBlank()) {
                _uiEvent.emit(InsertRepeatRoutineUiEvent.InsertCategoryNameEmpty)
                return@launch
            }
            insertCategoryUseCase(Category(insertCategoryText))
        }
    }

    fun onClickDayOfWeek(dayOfWeek : String) {
        _dayOfWeek.value = _dayOfWeek.value.map {
            if(it.date == dayOfWeek) it.copy(isSelected =  !it.isSelected)
            else it
        }
    }

    fun onClickRoutineInsert(routineName: String,selectedDayOfWeek : List<String>) {
        viewModelScope.launch {
            if (routineName.isBlank()) {
                _uiEvent.emit(InsertRepeatRoutineUiEvent.RoutineNameEmpty)
                return@launch
            }
            if (selectedDayOfWeek.isEmpty()) {
                _uiEvent.emit(InsertRepeatRoutineUiEvent.InsertDayOfWeekEmpty)
                return@launch
            }
            insertRepeatRoutineUseCase(
                RepeatRoutine(
                    text = routineName,
                    dayOfWeekList = selectedDayOfWeek,
                    category = selectedCategory.value ?: ""
                )
            )
            _uiEvent.emit(InsertRepeatRoutineUiEvent.InsertSuccess)
        }
    }
}
