package com.example.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.date.*
import com.example.domain.usecase.routine.GetRoutineListByDateUseCase
import com.example.domain.usecase.routine.InsertAllDailyRoutineFromRepeatRoutineUseCase
import com.example.domain.usecase.notification.SetNotiSendValueUseCase
import com.hegunhee.common.util.getTodayDate
import com.hegunhee.common.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val setCurrentDateUseCase: SetCurrentDateUseCase,
    private val setNotiSendValueUseCase: SetNotiSendValueUseCase,
    private val getRoutineListByDateUseCase: GetRoutineListByDateUseCase,
    private val insertDateUseCase: InsertDateUseCase,
    private val insertAllDailyRoutineFromRepeatRoutineUseCase: InsertAllDailyRoutineFromRepeatRoutineUseCase,
    private val isAppFirstOpenUseCase: IsAppFirstOpenUseCase,
    private val setAppFirstOpenedUseCase: SetAppFirstOpenedUseCase
) : ViewModel() {

    init {
        checkDate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkDate() = viewModelScope.launch(Dispatchers.IO) {
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

    fun setAppFirstOpened(){
        setAppFirstOpenedUseCase()
    }




}