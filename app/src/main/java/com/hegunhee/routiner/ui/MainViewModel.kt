package com.hegunhee.routiner.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.routiner.db.SharedPreferenceManager
import com.hegunhee.routiner.domain.GetRoutineListByDateUseCase
import com.hegunhee.routiner.domain.InsertDateUseCase
import com.hegunhee.routiner.util.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val getRoutineListByDateUseCase: GetRoutineListByDateUseCase,
    private val insertDateUseCase: InsertDateUseCase

) : ViewModel() {

    init {
        checkDate()
    }

    private fun checkDate() = viewModelScope.launch(Dispatchers.IO) {
        val sharedPreferenceCurrentDate = sharedPreferenceManager.getCurrentDate()
        if (sharedPreferenceCurrentDate == SharedPreferenceManager.CURRENT_DEFAULT_DATE) {
            // 앱을 처음 킨 상태라면
        } else if (sharedPreferenceCurrentDate != getCurrentDate()) {
            val currentDateRoutineList = getRoutineListByDateUseCase(sharedPreferenceCurrentDate)
            if (currentDateRoutineList.isNotEmpty()) {
                insertDateUseCase(sharedPreferenceCurrentDate)
            }
        }
        sharedPreferenceManager.setCurrentDate(getCurrentDate())
    }
}