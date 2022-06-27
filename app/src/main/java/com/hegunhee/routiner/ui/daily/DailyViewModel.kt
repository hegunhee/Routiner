package com.hegunhee.routiner.ui.daily

import android.util.Log
import androidx.lifecycle.*
import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.db.RoutineDao
import com.hegunhee.routiner.db.SharedPreferenceManager
import com.hegunhee.routiner.domain.DeleteAllByRoutineByDate
import com.hegunhee.routiner.domain.GetAllDailyRoutineUseCase
import com.hegunhee.routiner.domain.InsertDailyRoutineUseCase
import com.hegunhee.routiner.util.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getAllDailyRoutineUseCase: GetAllDailyRoutineUseCase,
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase,
    private val deleteAllByRoutineByDate: DeleteAllByRoutineByDate
) : ViewModel() {

    val dailyRoutineListLiveData: LiveData<List<Routine>> =
        getAllDailyRoutineUseCase(getCurrentDate()).asLiveData()

    val recyclerViewVisible: LiveData<Boolean> = Transformations.map(dailyRoutineListLiveData) {
        Log.d("RoutineCheck","check"+ it.toString())
        dailyRoutineListLiveData.value?.isNotEmpty()
    }

    val dailyRoutineprogress : LiveData<String> = Transformations.map(dailyRoutineListLiveData){
        return@map if(it.isEmpty()){
            "0 / 0"
        }else{
            "${it.count { it.isFinished }} / ${it.size}"
        }
    }

    fun onClickRoutineInsert() = viewModelScope.launch{
        // 버튼을 클릭했을때 일어나는 일

        insertDailyRoutineUseCase(Routine(getCurrentDate(),"테스트"))
    }

    fun insertRoutine() = viewModelScope.launch {

    }




}