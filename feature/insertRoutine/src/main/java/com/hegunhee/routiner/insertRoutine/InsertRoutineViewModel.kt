package com.hegunhee.routiner.insertRoutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.usecase.category.GetAllCategoryListByFlowUseCase
import com.hegunhee.category.CategoryActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertRoutineViewModel @Inject constructor(
    private val getAllCategoryListByFlowUseCase: GetAllCategoryListByFlowUseCase
) : ViewModel(), InsertRoutineActionHandler, CategoryActionHandler {

    val routineQuery : MutableStateFlow<String> = MutableStateFlow("")

    val categoryList : StateFlow<List<Category>> = getAllCategoryListByFlowUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    private val _navigationActions : MutableSharedFlow<InsertRoutineNavigationAction> = MutableSharedFlow()
    val navigationActions : SharedFlow<InsertRoutineNavigationAction> = _navigationActions.asSharedFlow()

    override fun onInsertRoutineButtonClick() {
        viewModelScope.launch {
            _navigationActions.emit(InsertRoutineNavigationAction.InsertRoutine)
        }
    }

    override fun onClickCategory(categoryText: String, isCategorySelected: Boolean) {

    }
}