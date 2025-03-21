package com.example.main.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.date.GetCurrentDateUseCase
import com.example.domain.usecase.date.InsertDateUseCase
import com.example.domain.usecase.date.IsAppFirstOpenUseCase
import com.example.domain.usecase.date.SetAppFirstOpenedUseCase
import com.example.domain.usecase.date.SetCurrentDateUseCase
import com.example.domain.usecase.repeatRoutine.GetAllRepeatRoutineListUseCase
import com.example.domain.usecase.routine.InsertRoutinesFromRepeatByDayOfWeekRoutineUseCase
import com.example.main.screen.MainUiState.FirstOpenApp
import com.example.main.screen.MainUiState.Init
import com.example.main.screen.MainUiState.InitDate
import com.example.main.screen.MainUiState.InsertRepeatRoutine
import com.example.main.screen.MainUiState.Success
import com.hegunhee.routiner.util.getTodayDate
import com.hegunhee.routiner.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getIsAppFirstOpenUseCase: IsAppFirstOpenUseCase,
    private val setAppFirstOpenedUseCase: SetAppFirstOpenedUseCase,
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val setCurrentDateUseCase: SetCurrentDateUseCase,
    private val getRepeatRoutinesUseCase: GetAllRepeatRoutineListUseCase,
    private val insertRoutinesFromRepeatRoutinesUseCase: InsertRoutinesFromRepeatByDayOfWeekRoutineUseCase,
    private val insertDateUseCase: InsertDateUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(Init)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun onAction(uiState: MainUiState) {
        viewModelScope.launch {
            when (uiState) {
                Init -> setAppFirstOpen()
                FirstOpenApp -> initTodayDate()
                InitDate -> initTodayDate()
                InsertRepeatRoutine -> initRepeatRoutine()
                Success -> {}
            }
        }
    }

    private suspend fun setAppFirstOpen() {
        if (getIsAppFirstOpenUseCase()) {
            setAppFirstOpenedUseCase()
        }
        _uiState.value = FirstOpenApp
    }

    private suspend fun initTodayDate() {
        val todayDate = getTodayDate()
        val currentDate = getCurrentDateUseCase()
        if (currentDate != todayDate) {
            insertDateUseCase(currentDate)
            setCurrentDateUseCase(todayDate)
            _uiState.value = InsertRepeatRoutine
        } else {
            _uiState.value = Success

        }
    }

    private suspend fun initRepeatRoutine() {
        if (getRepeatRoutinesUseCase().any { it.containsDayOfWeek(getTodayDayOfWeekFormatedKorean()) }) {
            insertRoutinesFromRepeatRoutinesUseCase(getTodayDayOfWeekFormatedKorean())
        }
        _uiState.value = Success
    }

}
