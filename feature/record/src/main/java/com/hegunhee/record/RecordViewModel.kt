package com.hegunhee.record

import androidx.lifecycle.*
import com.example.domain.model.Date
import com.example.domain.model.Review
import com.example.domain.model.Routine
import com.example.domain.usecase.date.GetAllDateListUseCase
import com.example.domain.usecase.review.DeleteReviewUseCase
import com.example.domain.usecase.review.GetReviewOrNullByDateUseCase
import com.example.domain.usecase.review.InsertReviewUseCase
import com.example.domain.usecase.routine.GetRoutineListByDateUseCase
import com.hegunhee.common.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getAllDateListUseCase: GetAllDateListUseCase,
    private val getRoutineListByDateUseCase: GetRoutineListByDateUseCase,
    private val getReviewOrNullByDateUseCase: GetReviewOrNullByDateUseCase,
    private val insertReviewUseCase: InsertReviewUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase

) : ViewModel() {

    private val _recordIsEmpty: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val recordIsEmpty: StateFlow<Boolean> = _recordIsEmpty.asStateFlow()

    private val _currentDate : MutableStateFlow<String> = MutableStateFlow(DATE_INITALVALUE)
    val currentDate : StateFlow<String> = _currentDate.asStateFlow()

    private val _currentDateListIndex : MutableStateFlow<Int> = MutableStateFlow(DEFAULT_DATE_INDEX)
    val currentDateListIndex : StateFlow<Int> = _currentDateListIndex.asStateFlow()

    private val _currentRoutineList: MutableStateFlow<List<Routine>> = MutableStateFlow(emptyList())
    val currentRoutineListState: StateFlow<List<Routine>> = _currentRoutineList.asStateFlow()

    var currentRoutineProgress : StateFlow<String> = MutableStateFlow<String>("")

    private val _review: MutableStateFlow<ReviewState> = MutableStateFlow(ReviewState.Uninitalized)
    private val review: StateFlow<ReviewState> = _review.asStateFlow()


    private val _reviewIsEmpty: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val reviewIsEmpty: StateFlow<Boolean> = _reviewIsEmpty.asStateFlow()


    var reviewText : StateFlow<String> = MutableStateFlow<String>("")


    val reviewEditText: MutableStateFlow<String> = MutableStateFlow("")

    private val _dateList : MutableStateFlow<List<Date>> = MutableStateFlow(emptyList())
    val dateList : StateFlow<List<Date>> = _dateList.asStateFlow()

    init {
        initRecordDateList()
        initCombine()
    }

    private fun initRecordDateList() = viewModelScope.launch {
        _dateList.value = getAllDateListUseCase()
        _recordIsEmpty.emit(dateList.value.isEmpty())
        if (dateList.value.isNotEmpty()) {
            initRecentRecord()
        }
    }

    private fun initCombine() = viewModelScope.launch {
        reviewText = review.map {
            return@map if(it is ReviewState.Success){
                it.review.review
            }else{
                ""
            }
        }.stateIn(viewModelScope)

        currentRoutineProgress = currentRoutineListState.map {
            return@map if(it.isEmpty()){
                ""
            }else{
                "${it.count{it.isFinished}} / ${it.size}"
            }
        }.stateIn(viewModelScope)
    }

    private fun initRecentRecord() = viewModelScope.launch {
        if(currentDateListIndex.value == DEFAULT_DATE_INDEX) {
            val recentDate = dateList.value.filter {it.date < getTodayDate() }.maxByOrNull { it.date }
            recentDate?.let { date ->
                setDateInfo(date.date)
            }
        }
    }

    fun setPreviousDate() = viewModelScope.launch {
        if (currentDateListIndex.value > 0) {
            setDateInfo(dateList.value[getPreviousIndex()].date)
        }
    }

    private fun getPreviousIndex() : Int {
        return _currentDateListIndex.value-1
    }

    fun setNextDate() = viewModelScope.launch {
        if (currentDateListIndex.value < dateList.value.size - 1) {
            setDateInfo(dateList.value[getNextIndex()].date)
        }
    }

    private suspend fun setDateInfo(date: Int) {
        setRecordRoutine(date)
        setReviewExist(date)
        _currentDate.emit(date.toString())
        _currentDateListIndex.value = dateList.value.indexOf(Date(date))
    }

    private fun getNextIndex() : Int{
        return currentDateListIndex.value + 1
    }

    private suspend fun setRecordRoutine(date: Int) {
        _currentRoutineList.emit((getRoutineListByDateUseCase(date)))
    }

    private suspend fun setReviewExist(date: Int) {
        getReviewOrNullByDateUseCase(date)?.let { review ->
            _review.emit(ReviewState.Success(review))
            _reviewIsEmpty.emit(false)
        } ?: kotlin.run {
            _review.emit(ReviewState.Empty)
            _reviewIsEmpty.emit(true)
        }
    }

    fun addReview() = viewModelScope.launch(Dispatchers.IO) {
        val reviewText = reviewEditText.value
        if(reviewText.isNotBlank()){
            val date = currentDate.value
            val review = Review(date.toInt(),reviewText)
            insertReviewUseCase(review)
            _review.emit(ReviewState.Success(review))
            _reviewIsEmpty.emit(false)
            reviewEditText.emit("")
        }
    }

    fun deleteReview() = viewModelScope.launch(Dispatchers.IO){
        (review.value as? ReviewState.Success)?.let { successReview ->
            deleteReviewUseCase(successReview.review)
            _review.emit(ReviewState.Empty)
            _reviewIsEmpty.emit(true)
        }
    }

    fun reviseReview() = viewModelScope.launch{
        (review.value as? ReviewState.Success)?.let {  _ ->
            _reviewIsEmpty.emit(true)
            reviewEditText.emit(reviewText.value)
        }
    }

    companion object {
        const val DATE_INITALVALUE = "기록이 존재하지 않습니다."
        const val DEFAULT_DATE_INDEX = -1
    }
}