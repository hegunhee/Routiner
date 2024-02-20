package com.hegunhee.routiner.insertRoutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.model.Routine
import com.example.domain.usecase.category.GetAllCategoryListByFlowUseCase
import com.example.domain.usecase.category.InsertCategoryUseCase
import com.example.domain.usecase.category.RemoveCategoryUseCase
import com.example.domain.usecase.routine.GetRoutineListByDateUseCase
import com.example.domain.usecase.routine.InsertRoutineUseCase
import com.hegunhee.category.CategoryActionHandler
import com.hegunhee.routiner.util.getTodayDate
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
    private val getRoutineListByDateUseCase: GetRoutineListByDateUseCase,
    private val insertRoutineUseCase: InsertRoutineUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase,
    private val removeCategoryUseCase: RemoveCategoryUseCase
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

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()


    private val _navigationActions : MutableSharedFlow<InsertRoutineNavigationAction> = MutableSharedFlow()
    val navigationActions : SharedFlow<InsertRoutineNavigationAction> = _navigationActions.asSharedFlow()

    override fun onInsertRoutineButtonClick() {
        viewModelScope.launch {
            val routineText = routineQuery.value
            if(routineText.isBlank()) {
                return@launch
            }
            getRoutineListByDateUseCase(getTodayDate()).let { routineList ->
                if(routineList.map(Routine::text).contains(routineText)){
                    _toastMessage.emit("중복된 루틴입니다.")
                }else{
                    val categoryText = if(selectedCategory.value.isSelected){
                        selectedCategory.value.name
                    }else{
                        ""
                    }
                    insertRoutineUseCase(Routine(date= getTodayDate(),text = routineText, category = categoryText))
                    _navigationActions.emit(InsertRoutineNavigationAction.InsertRoutine)
                }
            }
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

    override fun onSelectCategory(categoryText: String, isCategorySelected: Boolean) {
        viewModelScope.launch {
            _selectedCategory.value = Category(categoryText,!isCategorySelected)
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
}