package com.hegunhee.feature.mainActivity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.SharedPreferenceManager
import com.example.domain.usecase.date.InsertDateUseCase
import com.example.domain.usecase.routine.GetRoutineListByDateUseCase
import com.example.domain.usecase.routine.InsertAllDailyRoutineFromRepeatRoutineUseCase
import com.example.domain.usecase.date.GetCurrentDateUseCase
import com.example.domain.usecase.date.GetDefaultCurrentDateUseCase
import com.example.domain.usecase.date.SetCurrentDateUseCase
import com.hegunhee.feature.util.getTodayDate
import com.hegunhee.feature.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getDefaultCurrentDateUseCase: GetDefaultCurrentDateUseCase,
    private val setCurrentDateUseCase: SetCurrentDateUseCase,
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val getRoutineListByDateUseCase: GetRoutineListByDateUseCase,
    private val insertDateUseCase: InsertDateUseCase,
    private val insertAllDailyRoutineFromRepeatRoutineUseCase: InsertAllDailyRoutineFromRepeatRoutineUseCase
) : ViewModel() {

    init {
        checkDate()
    }

    private var _firstAppOpenEvent = MutableLiveData<FirstAppOpenEvent>(FirstAppOpenEvent.UnInitalized)
    val firstAppOpenEvent : LiveData<FirstAppOpenEvent>
    get() = _firstAppOpenEvent

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkDate() = viewModelScope.launch(Dispatchers.IO) {
        val sharedPreferenceCurrentDate = getCurrentDateUseCase()
        if (sharedPreferenceCurrentDate == getDefaultCurrentDateUseCase()){
            _firstAppOpenEvent.postValue(FirstAppOpenEvent.OpenDialog)
        } else if (sharedPreferenceCurrentDate != getTodayDate()) {
            insertAllDailyRoutineFromRepeatRoutineUseCase(getTodayDayOfWeekFormatedKorean())
            val currentDateRoutineList = getRoutineListByDateUseCase(sharedPreferenceCurrentDate)
            if (currentDateRoutineList.isNotEmpty()) {
                insertDateUseCase(sharedPreferenceCurrentDate)
            }
        }
        setCurrentDateUseCase(getTodayDate())
    }

    fun setEventFinish() = viewModelScope.launch {
        _firstAppOpenEvent.postValue(FirstAppOpenEvent.Finished)
    }

    fun setInitNotiValue(notiValue : Boolean){
        sharedPreferenceManager.setNofiSendValue(notiValue)
    }




}