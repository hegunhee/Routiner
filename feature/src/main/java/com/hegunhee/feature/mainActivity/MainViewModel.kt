package com.hegunhee.feature.mainActivity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Routine
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
    private fun checkDate() = viewModelScope.launch {
        val sharedPreferenceCurrentDate = getCurrentDateUseCase()
        if (sharedPreferenceCurrentDate == getDefaultCurrentDateUseCase()){
            _firstAppOpenEvent.emit(Unit)
        } else if (sharedPreferenceCurrentDate != getTodayDate()) {
            insertAllDailyRoutineFromRepeatRoutineUseCase(getTodayDayOfWeekFormatedKorean())
            val currentDateRoutineList = getRoutineListByDateUseCase(sharedPreferenceCurrentDate)
            if (currentDateRoutineList.isNotEmpty()) {
                insertDateUseCase(sharedPreferenceCurrentDate)
            }
        }

        // 지금 현재 조건이
        // 오늘이 만약에 처음 앱을 킨 날짜인지
        // 오늘 날짜와 앱을 가장 최근에 킨 날짜가 같은지
        // 그리고 오늘 날짜로 저장된 예약을 불러오는것에 관련됨
        setCurrentDateUseCase(getTodayDate())
    }

    fun setInitNotiValue(notiValue : Boolean){
        setNotiSendValueUseCase(notiValue)
    }




}