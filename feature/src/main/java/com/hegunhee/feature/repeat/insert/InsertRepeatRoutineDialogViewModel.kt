package com.hegunhee.feature.repeat.insert

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.usecase.category.GetAllCategoryListByFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertRepeatRoutineDialogViewModel @Inject constructor(
    private val allCategoryListByFlowUseCase: GetAllCategoryListByFlowUseCase
) : ViewModel() , InsertRepeatRoutineActionHandler{

    val repeatRoutineText : MutableStateFlow<String> = MutableStateFlow<String>("")

    var categoryText : String = ""

    private val _categoryList : Flow<List<Category>> = allCategoryListByFlowUseCase()
    val categoryList : Flow<List<Category>>
    get() = _categoryList

    private val _navigationActions : MutableSharedFlow<InsertRepeatRoutineNavigationAction> = MutableSharedFlow<InsertRepeatRoutineNavigationAction>()
    val navigationActions : SharedFlow<InsertRepeatRoutineNavigationAction> = _navigationActions.asSharedFlow()

    override fun cancelRepeatRoutine() {
        viewModelScope.launch {
            _navigationActions.emit(InsertRepeatRoutineNavigationAction.DisMissDialog)
        }
    }

    override fun successRepeatRoutine() {
        println(repeatRoutineText)
        println(categoryText)
    }

    override fun openInsertCategoryDialog() {
        viewModelScope.launch {
            _navigationActions.emit(InsertRepeatRoutineNavigationAction.OpenInsertCategoryDialog)
        }
    }


}