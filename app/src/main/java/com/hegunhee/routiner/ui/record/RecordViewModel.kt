package com.hegunhee.routiner.ui.record

import android.util.Log
import androidx.lifecycle.*
import com.hegunhee.routiner.domain.GetAllDailyRoutineUseCase
import com.hegunhee.routiner.domain.GetAllDateUseCase
import com.hegunhee.routiner.domain.GetRoutineListByDate
import com.hegunhee.routiner.util.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getAllDateUseCase: GetAllDateUseCase,
    private val getRoutineListByDateUseCase: GetRoutineListByDate

) : ViewModel() {

    private var _recordIsEmpty: MutableLiveData<Boolean> = MutableLiveData(false)
    val recordIsEmpty: LiveData<Boolean>
        get() = _recordIsEmpty

    private var _currentDate: MutableLiveData<String> = MutableLiveData(DATE_INITALVALUE)
    val currentDate: LiveData<String>
        get() = _currentDate

    private var _currentRoutineList: MutableLiveData<RoutineListState> =
        MutableLiveData(RoutineListState.Uninitalized)
    val currentRoutineListState: LiveData<RoutineListState>
        get() = _currentRoutineList

    val currentRoutineProgress = Transformations.map(currentRoutineListState){
        return@map when(it){
            is RoutineListState.Uninitalized -> {""}
            is RoutineListState.Success -> {
                "${it.routineList.count { it.isFinished }} / ${it.routineList.size}"
            }
        }
    }


    init {
        initFun()
    }

    private fun initFun() = viewModelScope.launch(Dispatchers.IO) {
        val allDate = getAllDateUseCase()
        Log.d("allDate", allDate.toString())
        _recordIsEmpty.postValue(true)
        if (allDate.isEmpty()) {
            _recordIsEmpty.postValue(true)
        } else {
            _recordIsEmpty.postValue(false)
            setLeftData()
        }
    }

    fun setLeftData() = viewModelScope.launch(Dispatchers.IO) {
        if (currentDate.value == DATE_INITALVALUE) {
            val leftDate = getAllDateUseCase().map { it.date }.filter { it < getCurrentDate() }.maxOrNull()
            if (leftDate == null) {
                Log.d("currentDateTest", "조회할 이전 데이터가 없습니다. INIT")
                // 존재하지 않습니다~
            } else {
                _currentDate.postValue(leftDate.toString())
                setRecordRoutine(leftDate)
            }
        } else {
            val leftDay = getAllDateUseCase().map { it.date }.filter { it < currentDate.value!!.toInt() }
                    .maxOrNull()
            if (leftDay == null) {
                Log.d("currentDateTest", "조회할 이전 데이터가 없습니다. ")
                // 존재하지 않습니다~
            } else {
                _currentDate.postValue(leftDay.toString())
                setRecordRoutine(leftDay)
            }
        }
    }

    fun setRightDate() = viewModelScope.launch(Dispatchers.IO) {
        if (currentDate.value == DATE_INITALVALUE) {
            val rightDate = getAllDateUseCase().map { it.date }.filter { it > getCurrentDate() }.minOrNull()
            if (rightDate == null) {
                Log.d("currentDateTest", "조회할 이후 데이터가 없습니다. INIT")
                // 존재하지 않습니다~
            } else {
                _currentDate.postValue(rightDate.toString())
                setRecordRoutine(rightDate)
            }
        } else {
            val rightDate = getAllDateUseCase().map { it.date }.filter { it > currentDate.value!!.toInt() }
                    .minOrNull()
            if (rightDate == null) {
                Log.d("currentDateTest", "조회할 이후 데이터가 없습니다.")
                // 존재하지 않습니다.
            } else {
                _currentDate.postValue(rightDate.toString())
                setRecordRoutine(rightDate)
            }
        }
    }

    private suspend fun setRecordRoutine(date: Int) {
        _currentRoutineList.postValue(RoutineListState.Success(getRoutineListByDateUseCase(date)))
    }

    companion object {
        const val DATE_INITALVALUE = "기록이 존재하지 않습니다."
    }
}