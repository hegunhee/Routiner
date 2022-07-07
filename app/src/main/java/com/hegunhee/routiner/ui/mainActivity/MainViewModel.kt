package com.hegunhee.routiner.ui.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.routiner.db.SharedPreferenceManager
import com.hegunhee.routiner.domain.GetRoutineListByDateUseCase
import com.hegunhee.routiner.domain.InsertAllDailyRoutineUseCase
import com.hegunhee.routiner.domain.InsertDateUseCase
import com.hegunhee.routiner.util.getTodayDate
import com.hegunhee.routiner.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val getRoutineListByDateUseCase: GetRoutineListByDateUseCase,
    private val insertDateUseCase: InsertDateUseCase,
    private val insertAllDailyRoutineUseCase: InsertAllDailyRoutineUseCase
) : ViewModel() {

    init {
        checkDate()
    }

    private var _firstAppOpenEvent = MutableLiveData<FirstAppOpenEvent>(FirstAppOpenEvent.UnInitalized)
    val firstAppOpenEvent : LiveData<FirstAppOpenEvent>
    get() = _firstAppOpenEvent

    private fun checkDate() = viewModelScope.launch(Dispatchers.IO) {
        val sharedPreferenceCurrentDate = sharedPreferenceManager.getCurrentDate()
        if (sharedPreferenceCurrentDate == SharedPreferenceManager.CURRENT_DATE_DEFAULT_DATE) {
            _firstAppOpenEvent.postValue(FirstAppOpenEvent.OpenDialog)
        } else if (sharedPreferenceCurrentDate != getTodayDate()) {
            insertAllDailyRoutineUseCase(getTodayDayOfWeekFormatedKorean())
            val currentDateRoutineList = getRoutineListByDateUseCase(sharedPreferenceCurrentDate)
            if (currentDateRoutineList.isNotEmpty()) {
                insertDateUseCase(sharedPreferenceCurrentDate)
            }
            // 오늘날짜로 저장되어있는 거시기를 저장한다.

        }
        sharedPreferenceManager.setCurrentDate(getTodayDate())
    }

    fun setInitNotiValue(notiValue : Boolean){
        sharedPreferenceManager.setNofiSendValue(notiValue)
    }

    fun setEventFinish() = viewModelScope.launch {
        _firstAppOpenEvent.postValue(FirstAppOpenEvent.Finished)
    }


}