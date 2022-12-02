package com.hegunhee.feature.category.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertCategoryDialogViewModel @Inject constructor(): ViewModel(), InsertCategoryDialogActionHandler {

    val categoryText : MutableStateFlow<String> = MutableStateFlow<String>("")

    private val _dismissDialog : MutableSharedFlow<Unit> = MutableSharedFlow<Unit>()
    val dismissDialog : SharedFlow<Unit> = _dismissDialog.asSharedFlow()

    private val _toastMessage : MutableSharedFlow<String> = MutableSharedFlow<String>()
    val toastMessage : SharedFlow<String> = _toastMessage.asSharedFlow()

    override fun cancelCategory() {
        viewModelScope.launch {
            _dismissDialog.emit(Unit)
        }
    }

    override fun insertCategory() {
        viewModelScope.launch {
            _toastMessage.emit(categoryText.value)
        }
    }


}