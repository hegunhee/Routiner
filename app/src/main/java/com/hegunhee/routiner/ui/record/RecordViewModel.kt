package com.hegunhee.routiner.ui.record

import android.util.Log
import androidx.lifecycle.*
import com.hegunhee.routiner.domain.GetAllDateUseCase
import com.hegunhee.routiner.util.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getAllDateUseCase: GetAllDateUseCase

) : ViewModel() {

    private var _recordIsEmpty: MutableLiveData<Boolean> = MutableLiveData(false)
    val recordIsEmpty: LiveData<Boolean>
        get() = _recordIsEmpty

    private var _currentDate: MutableLiveData<CurrentDateState> =
        MutableLiveData(CurrentDateState.Uninitalized)
    val currentDate: LiveData<CurrentDateState>
        get() = _currentDate


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
            // 아니면 현재 가장 가까운 날짜 찾아서 집어넣자
        }
    }

    fun setLeftData() = viewModelScope.launch(Dispatchers.IO) {
        with(currentDate.value!!) {
            when (this) {
                is CurrentDateState.SetData -> {
                    this.date
                }
                CurrentDateState.Uninitalized -> {
                    val leftDay = getAllDateUseCase().map { it.date }.filter { it < getCurrentDate()}.maxOrNull()
                    if (leftDay == null){
                        Log.d("dateTest","isNull")
                    }else{
                        _currentDate.postValue(CurrentDateState.SetData(leftDay))
                        Log.d("dateTest","isNotNull $leftDay")
                    }
                }
            }
        }
    }
}

fun setRightData() {

}

//    private fun setDate()