package com.example.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.date.InsertDateUseCase
import com.example.domain.usecase.routine.GetRoutineListByDateUseCase
import com.example.domain.usecase.routine.InsertAllDailyRoutineFromRepeatRoutineUseCase
import com.example.domain.usecase.date.GetCurrentDateUseCase
import com.example.domain.usecase.date.GetDefaultCurrentDateUseCase
import com.example.domain.usecase.date.SetCurrentDateUseCase
import com.example.domain.usecase.notification.SetNotiSendValueUseCase
import com.hegunhee.feature.util.getTodayDate
import com.hegunhee.feature.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getDefaultCurrentDateUseCase: GetDefaultCurrentDateUseCase,
    private val setCurrentDateUseCase: SetCurrentDateUseCase,
    private val setNotiSendValueUseCase: SetNotiSendValueUseCase,
    private val getRoutineListByDateUseCase: GetRoutineListByDateUseCase,
    private val insertDateUseCase: InsertDateUseCase,
    private val insertAllDailyRoutineFromRepeatRoutineUseCase: InsertAllDailyRoutineFromRepeatRoutineUseCase
) : ViewModel() {

    init {
        checkDate()
    }


    private val _firstAppOpenEvent : MutableSharedFlow<Unit> = MutableSharedFlow<Unit>()
    val firstAppOpenEvent : SharedFlow<Unit> = _firstAppOpenEvent.asSharedFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkDate() = viewModelScope.launch(Dispatchers.IO){
        val currentLoadedDate = getCurrentDateUseCase()
        if (currentLoadedDate == getDefaultCurrentDateUseCase()){
            _firstAppOpenEvent.emit(Unit)
        } else if (currentLoadedDate != getTodayDate()) {
            insertAllDailyRoutineFromRepeatRoutineUseCase(getTodayDayOfWeekFormatedKorean())
            val currentDateRoutineList = getRoutineListByDateUseCase(currentLoadedDate)
            if (currentDateRoutineList.isNotEmpty()) {
                insertDateUseCase(currentLoadedDate)
            }
        }
        setCurrentDateUseCase(getTodayDate())
    }

    fun setInitNotiValue(notiValue : Boolean){
        setNotiSendValueUseCase(notiValue)
    }




}