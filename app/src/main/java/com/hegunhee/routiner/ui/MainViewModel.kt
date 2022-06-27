package com.hegunhee.routiner.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.routiner.db.SharedPreferenceManager
import com.hegunhee.routiner.domain.GetAllDailyRoutineUseCase
import com.hegunhee.routiner.domain.GetRoutineListByDate
import com.hegunhee.routiner.domain.InsertDateUseCase
import com.hegunhee.routiner.util.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val getRoutineListByDate: GetRoutineListByDate,
    private val insertDateUseCase: InsertDateUseCase

) : ViewModel() {

    init {
        Log.d("MainViewModel","mainViewModel init")
    }

    fun checkDate() = viewModelScope.launch {
        val sharedPreferenceCurrentDate = sharedPreferenceManager.getCurrentDate()
        if(sharedPreferenceCurrentDate == SharedPreferenceManager.CURRENT_DEFAULT_DATE){
            Log.d("sharedTest","Default Value")
        }else if(sharedPreferenceCurrentDate != getCurrentDate()){
            Log.d("sharedTest","is not same")
            if(getRoutineListByDate(sharedPreferenceCurrentDate).isEmpty()){
                Log.d("sharedTest","isEmpty")
            }else{
                Log.d("sharedTest","isNotEmpty")
                insertDateUseCase(sharedPreferenceCurrentDate)
            }

        }else if(sharedPreferenceCurrentDate == getCurrentDate()){
            Log.d("sharedTest","is Same")
        }
        sharedPreferenceManager.setCurrentDate(getCurrentDate())
    }
}