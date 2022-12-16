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
class InsertCategoryDialogViewModel @Inject constructor(
    private val insertCategoryUseCase: InsertCategoryUseCase,
): ViewModel(), InsertCategoryDialogActionHandler {

    val categoryText : MutableStateFlow<String> = MutableStateFlow<String>("")

    private val _dismissDialog : MutableSharedFlow<Unit> = MutableSharedFlow<Unit>()
    val dismissDialog : SharedFlow<Unit> = _dismissDialog.asSharedFlow()

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow<String>()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    private val emptyMessage = "입력이 비어있습니다."
    private val successMessage = "저장되었습니다."

    override fun cancelCategory() {
        viewModelScope.launch {
            _dismissDialog.emit(Unit)
        }
    }

    override fun insertCategory() {
        viewModelScope.launch {
            val categoryText = categoryText.value
            if(categoryText.isBlank()){
                _toastMessage.emit(emptyMessage)
            }else{
                insertCategoryUseCase(Category(categoryText))
                _toastMessage.emit(successMessage)
                _dismissDialog.emit(Unit)
            }
        }
    }
}