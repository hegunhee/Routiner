package com.hegunhee.routiner.insertRoutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.usecase.category.GetAllCategoryListByFlowUseCase
import com.example.domain.usecase.category.InsertCategoryUseCase
import com.hegunhee.category.CategoryActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
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
class InsertRoutineViewModel @Inject constructor(
    private val getAllCategoryListByFlowUseCase: GetAllCategoryListByFlowUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase
) : ViewModel(), InsertRoutineActionHandler, CategoryActionHandler {

    val routineQuery : MutableStateFlow<String> = MutableStateFlow("")

    val categoryQuery : MutableStateFlow<String> = MutableStateFlow("")

    private val _selectedCategory : MutableStateFlow<Category> = MutableStateFlow(Category(""))
    private val selectedCategory : StateFlow<Category> = _selectedCategory.asStateFlow()


    val categoryList : StateFlow<List<Category>> = getAllCategoryListByFlowUseCase().combine(selectedCategory) { categoryList, selectedCategory ->
        if(selectedCategory.name.isBlank()) {
            categoryList
        }else {
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

    private val _navigationActions : MutableSharedFlow<InsertRoutineNavigationAction> = MutableSharedFlow()
    val navigationActions : SharedFlow<InsertRoutineNavigationAction> = _navigationActions.asSharedFlow()

    override fun onInsertRoutineButtonClick() {
        viewModelScope.launch {
            _navigationActions.emit(InsertRoutineNavigationAction.InsertRoutine)
        }
    }

    override fun onCategoryAddClick() {
        val query = categoryQuery.value
        if(query.isBlank()) {
            return
        }
        viewModelScope.launch {
            insertCategoryUseCase(Category(name = query))
        }

    }

    override fun onClickCategory(categoryText: String, isCategorySelected: Boolean) {
        viewModelScope.launch {
            _selectedCategory.value = Category(categoryText,!isCategorySelected)
        }
    }
}