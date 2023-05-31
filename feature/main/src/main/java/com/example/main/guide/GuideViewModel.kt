package com.example.main.guide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.date.SetAppFirstOpenedUseCase
import com.example.domain.usecase.notification.SetNotiSendValueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val setAppFirstOpenedUseCase: SetAppFirstOpenedUseCase,
    private val setNotiSendValueUseCase : SetNotiSendValueUseCase
): ViewModel() {

    val isAllowNotification : MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)

    private val _navigateDismissDialog : MutableSharedFlow<Unit> = MutableSharedFlow()
    val navigateDismissDialog : SharedFlow<Unit> = _navigateDismissDialog.asSharedFlow()


    fun onClickDismissDialogButton() {
        viewModelScope.launch {
            setAppFirstOpenedUseCase()
            setNotiSendValueUseCase(isAllowNotification.value)
            _navigateDismissDialog.emit(Unit)
        }
    }
}