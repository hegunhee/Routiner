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
        getAllDailyRoutineUseCase(getCurrentDate()).asLiveData().apply {
            Log.d("RoutineCheck",this.value?.toString() ?: "no")
        }

    val recyclerViewVisible: LiveData<Boolean> = Transformations.map(dailyRoutineListLiveData) {
        Log.d("RoutineCheck","check"+ it.toString())
        dailyRoutineListLiveData.value?.isNotEmpty()
    }

    private var _onClickEvent : MutableLiveData<Event> = MutableLiveData(Event.Uninitalized)
    val onClickEvent : LiveData<Event>
    get() = _onClickEvent

    val dailyRoutineprogress : LiveData<String> = Transformations.map(dailyRoutineListLiveData){
        return@map if(it.isEmpty()){
            "0 / 0"
        }else{
            "${it.count { it.isFinished }} / ${it.size}"
        }
    }

    fun onClickRoutineInsert() = viewModelScope.launch{
        // 버튼을 클릭했을때 일어나는 일
        _onClickEvent.postValue(Event.Clicked)
    }

    fun insertRoutine(text : String) = viewModelScope.launch {
        //코드 작성 중복같은것도 해서
        val isExistSameText : Boolean = dailyRoutineListLiveData.value?.filter { it.text == text }?.size != 0

        if(isExistSameText){
            Log.d("insertRoutine","exist")
        }else{
            Log.d("insertRoutine","not exist")
            insertDailyRoutineUseCase(Routine(getCurrentDate(),text))
        }
    }




}