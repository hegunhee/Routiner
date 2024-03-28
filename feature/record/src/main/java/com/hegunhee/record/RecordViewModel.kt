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
import com.hegunhee.routiner.util.getTodayDate
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

    private val _dateList : MutableStateFlow<List<Date>> = MutableStateFlow(emptyList())
    val dateList : StateFlow<List<Date>> = _dateList.asStateFlow()

    private val _currentDateListIndex : MutableStateFlow<Int> = MutableStateFlow(DEFAULT_DATE_INDEX)
    val currentDateListIndex : StateFlow<Int> = _currentDateListIndex.asStateFlow()

    private val _currentDate : MutableStateFlow<String> = MutableStateFlow(DATE_INITALVALUE)
    val currentDate : StateFlow<String> = _currentDate.asStateFlow()

    private val _currentRoutineList: MutableStateFlow<List<Routine>> = MutableStateFlow(emptyList())
    val currentRoutineList: StateFlow<List<Routine>> = _currentRoutineList.asStateFlow()

    private val _reviewState: MutableStateFlow<ReviewState> = MutableStateFlow(ReviewState.Uninitalized)
    val reviewState: StateFlow<ReviewState> = _reviewState.asStateFlow()

    val reviewText : StateFlow<String> = reviewState.map {
        if(it is ReviewState.Success) {
            it.review.review
        }else {
            ""
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed(5000L),
    )

    val reviewEditText: MutableStateFlow<String> = MutableStateFlow("")

    init {
        viewModelScope.launch {
            initRecordDateList()
            initRecentRecord()
        }
    }

    private suspend fun initRecordDateList() {
        _dateList.value = getAllDateListUseCase()
    }
    private suspend fun initRecentRecord() {
        if(dateList.value.isEmpty()) return
        if(currentDateListIndex.value == DEFAULT_DATE_INDEX) {
            val recentDate = dateList.value.filter {it.date < getTodayDate() }.maxByOrNull { it.date }
            recentDate?.let { date ->
                setDateInfo(date)
            }
        }
    }

    fun setPreviousDate() = viewModelScope.launch {
        if (currentDateListIndex.value > 0) {
            setDateInfo(dateList.value[getPreviousIndex()])
        }
    }

    private fun getPreviousIndex() : Int {
        return _currentDateListIndex.value-1
    }

    fun setNextDate() = viewModelScope.launch {
        if (currentDateListIndex.value < dateList.value.size - 1) {
            setDateInfo(dateList.value[getNextIndex()])
        }
    }

    private suspend fun setDateInfo(date: Date) {
        setRecordRoutine(date.date)
        setReviewExist(date.date)
        _currentDate.emit(date.desc)
        _currentDateListIndex.value = dateList.value.indexOf(Date(date.date))
    }

    private fun getNextIndex() : Int{
        return currentDateListIndex.value + 1
    }

    private suspend fun setRecordRoutine(date: Int) {
        _currentRoutineList.emit((getRoutineListByDateUseCase(date)))
    }

    private suspend fun setReviewExist(date: Int) {
        getReviewOrNullByDateUseCase(date)?.let { review ->
            _reviewState.emit(ReviewState.Success(review))
        } ?: kotlin.run {
            _reviewState.emit(ReviewState.Empty)
        }
        reviewEditText.emit("")
    }

    fun addReview() = viewModelScope.launch(Dispatchers.IO) {
        if(reviewEditText.value.isBlank()) return@launch
        val date = currentDate.value
        val review = Review(date.toInt(), reviewEditText.value)
        insertReviewUseCase(review)
        _reviewState.emit(ReviewState.Success(review))
        reviewEditText.emit("")
    }

    fun deleteReview() = viewModelScope.launch(Dispatchers.IO){
        (reviewState.value as? ReviewState.Success)?.let { successReview ->
            deleteReviewUseCase(successReview.review)
            _reviewState.emit(ReviewState.Empty)
        }
    }

    fun reviseReview() = viewModelScope.launch{
        val currentReviewState = reviewState.value
        (currentReviewState as? ReviewState.Success)?.let { _ ->
            _reviewState.emit(ReviewState.Revise)
            reviewEditText.emit(currentReviewState.review.review)
        }
    }

    companion object {
        const val DATE_INITALVALUE = "기록이 존재하지 않습니다."
        const val DEFAULT_DATE_INDEX = -1
    }
}