package com.example.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.date.*
import com.example.domain.usecase.routine.GetRoutineListByDateUseCase
import com.example.domain.usecase.routine.InsertAllDailyRoutineFromRepeatRoutineUseCase
import com.hegunhee.routiner.util.getTodayDate
import com.hegunhee.routiner.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val setCurrentDateUseCase: SetCurrentDateUseCase,
    private val getRoutineListByDateUseCase: GetRoutineListByDateUseCase,
    private val insertDateUseCase: InsertDateUseCase,
    private val insertAllDailyRoutineFromRepeatRoutineUseCase: InsertAllDailyRoutineFromRepeatRoutineUseCase,
    private val isAppFirstOpenUseCase: IsAppFirstOpenUseCase,
) : ViewModel() {

    private val isGuideDialogOpen : MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        checkDayFirstOpen()
    }

    private fun checkDayFirstOpen() = viewModelScope.launch(Dispatchers.IO) {
        val currentLoadedDate = getCurrentDateUseCase()
        if (currentLoadedDate != getTodayDate()) {
            insertAllDailyRoutineFromRepeatRoutineUseCase(getTodayDayOfWeekFormatedKorean())
            val currentDateRoutineList = getRoutineListByDateUseCase(currentLoadedDate)
            if (currentDateRoutineList.isNotEmpty()) {
                insertDateUseCase(currentLoadedDate)
            }
        }
        setCurrentDateUseCase(getTodayDate())
    }

    fun isAppFirstOpen() : Boolean {
        return isAppFirstOpenUseCase()
    }

    fun isGuideDialogOpen() : Boolean {
        return if(isGuideDialogOpen.value) {
            true
        }else {
            isGuideDialogOpen.value = true
            false
        }
    }
}