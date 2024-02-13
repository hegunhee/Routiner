package com.hegunhee.category.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.usecase.category.InsertCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertCategoryViewModel @Inject constructor(
    private val insertCategoryUseCase: InsertCategoryUseCase,
): ViewModel(), InsertCategoryActionHandler {

    val categoryText : MutableStateFlow<String> = MutableStateFlow<String>("")

    private val _navigationActions : MutableSharedFlow<InsertCategoryNavigationAction> = MutableSharedFlow()
    val navigationActions : SharedFlow<InsertCategoryNavigationAction> = _navigationActions.asSharedFlow()

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow<String>()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    override fun cancelCategory() {
        viewModelScope.launch {
            _navigationActions.emit(InsertCategoryNavigationAction.DismissDialog)
        }
    }

    override fun insertCategory() {
        viewModelScope.launch {
            val categoryText = categoryText.value
            if(categoryText.isBlank()){
                _toastMessage.emit("입력이 비어있습니다.")
            }else{
                insertCategoryUseCase(Category(categoryText))
                _toastMessage.emit("저장되었습니다.")
                _navigationActions.emit(InsertCategoryNavigationAction.DismissDialog)
            }
        }
    }
}