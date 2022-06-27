package com.hegunhee.routiner.ui.daily

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.db.RoutineDao
import com.hegunhee.routiner.db.SharedPreferenceManager
import com.hegunhee.routiner.domain.GetAllDailyRoutineUseCase
import com.hegunhee.routiner.domain.InsertDailyRoutineUseCase
import com.hegunhee.routiner.util.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getAllDailyRoutineUseCase: GetAllDailyRoutineUseCase,
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {

    val dailyRoutineListLiveData: LiveData<List<Routine>> =
        getAllDailyRoutineUseCase(getCurrentDate()).asLiveData()

    val recyclerViewVisible: LiveData<Boolean> = Transformations.map(dailyRoutineListLiveData) {
        Log.d("RoutineCheck","check"+ it.toString())
        dailyRoutineListLiveData.value?.isEmpty()
    }



}