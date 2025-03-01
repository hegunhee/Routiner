package com.example.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.date.*
import com.example.domain.usecase.routine.GetRoutinesByDateUseCase
import com.example.domain.usecase.routine.InsertRoutinesFromRepeatByDayOfWeekRoutineUseCase
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
    private val getRoutinesByDateUseCase: GetRoutinesByDateUseCase,
    private val insertDateUseCase: InsertDateUseCase,
    private val insertRoutinesFromRepeatByDayOfWeekRoutineUseCase: InsertRoutinesFromRepeatByDayOfWeekRoutineUseCase,
    private val isAppFirstOpenUseCase: IsAppFirstOpenUseCase,
) : ViewModel() {

    private val isGuideDialogOpen : MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        checkDayFirstOpen()
    }

    private fun checkDayFirstOpen() = viewModelScope.launch(Dispatchers.IO) {
        val currentLoadedDate = getCurrentDateUseCase()
        if (currentLoadedDate != getTodayDate()) {
            insertRoutinesFromRepeatByDayOfWeekRoutineUseCase(getTodayDayOfWeekFormatedKorean())
            val currentDateRoutineList = getRoutinesByDateUseCase(currentLoadedDate)
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