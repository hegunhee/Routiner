package com.example.main.guide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.date.SetAppFirstOpenedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val setAppFirstOpenedUseCase: SetAppFirstOpenedUseCase,
): ViewModel() {


    private val _navigateDismissDialog : MutableSharedFlow<Unit> = MutableSharedFlow()
    val navigateDismissDialog : SharedFlow<Unit> = _navigateDismissDialog.asSharedFlow()


    fun onClickDismissDialogButton() {
        viewModelScope.launch {
            setAppFirstOpenedUseCase()
            _navigateDismissDialog.emit(Unit)
        }
    }
}