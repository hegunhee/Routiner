package com.hegunhee.routiner.insertRoutine.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.category.DeleteCategoryUseCase
import com.example.domain.usecase.category.GetCategoriesFlowUseCase
import com.example.domain.usecase.category.InsertCategoryUseCase
import com.example.domain.usecase.routine.InsertRoutineUseCase
import com.hegunhee.routiner.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import hegunhee.routiner.model.Category
import hegunhee.routiner.model.Routine
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertRoutineViewModel @Inject constructor(
    private val getCategoriesFlowUseCase: GetCategoriesFlowUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase,
    private val insertRoutineUseCase: InsertRoutineUseCase,
) : ViewModel() {

    private val categories = getCategoriesFlowUseCase()

    private val selectedCategory: MutableStateFlow<String?> = MutableStateFlow(null)

    val uiState: StateFlow<InsertRoutineUiState> =
        categories.combine(selectedCategory) { categories, selectedCategory ->
            if (selectedCategory == null) {
                InsertRoutineUiState.Categories(categories)
            } else {
                InsertRoutineUiState.Categories(categories.map { category ->
                    if (category.name == selectedCategory) category.copy(isSelected = true)
                    else category
                })
            }
        }.stateIn(
            scope = viewModelScope,
            initialValue = InsertRoutineUiState.Init,
            started = SharingStarted.WhileSubscribed(5_000)
        )

    private val _uiEvent: MutableSharedFlow<InsertRoutineUiEvent> = MutableSharedFlow()
    val uiEvent: SharedFlow<InsertRoutineUiEvent> = _uiEvent.asSharedFlow()

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
                _uiEvent.emit(InsertRoutineUiEvent.InsertCategoryNameEmpty)
                return@launch
            }
            insertCategoryUseCase(Category(insertCategoryText))
        }

    }

    fun onClickRoutineInsert(routineName: String) {
        viewModelScope.launch {
            if (routineName.isBlank()) {
                _uiEvent.emit(InsertRoutineUiEvent.RoutineNameEmpty)
                return@launch
            }
            insertRoutineUseCase(
                Routine(
                    date = getTodayDate(),
                    text = routineName,
                    category = selectedCategory.value ?: ""
                )
            )
            _uiEvent.emit(InsertRoutineUiEvent.InsertSuccess)
        }
    }
}
