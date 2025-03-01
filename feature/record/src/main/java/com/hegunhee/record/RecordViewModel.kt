package com.hegunhee.record

import androidx.lifecycle.*
import hegunhee.routiner.model.Date
import hegunhee.routiner.model.Review
import hegunhee.routiner.model.Routine
import com.example.domain.usecase.date.GetDateListUseCase
import com.example.domain.usecase.review.DeleteReviewUseCase
import com.example.domain.usecase.review.GetReviewOrNullByDateUseCase
import com.example.domain.usecase.review.InsertReviewUseCase
import com.example.domain.usecase.routine.GetRoutinesByDateUseCase
import com.hegunhee.record.dateSelector.DateSelectorActionHandler
import com.hegunhee.routiner.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getDateListUseCase: GetDateListUseCase,
    private val getRoutinesByDateUseCase: GetRoutinesByDateUseCase,
    private val getReviewOrNullByDateUseCase: GetReviewOrNullByDateUseCase,
    private val insertReviewUseCase: InsertReviewUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase
) : ViewModel() , DateSelectorActionHandler{

    private val _dateList : MutableStateFlow<List<Date>> = MutableStateFlow(emptyList())
    val dateList : StateFlow<List<Date>> = _dateList.asStateFlow()

    private val _currentDate : MutableStateFlow<Date> = MutableStateFlow(Date.EMPTY)
    val currentDate : StateFlow<Date> = _currentDate.asStateFlow()

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
        _dateList.value = getDateListUseCase()
    }
    private suspend fun initRecentRecord() {
        if(dateList.value.isEmpty()) return
        val recentDate = dateList.value.filter {it.date < getTodayDate() }.maxByOrNull { it.date }
        recentDate?.let { date ->
            setDateInfo(date)
        }
    }

    private suspend fun setDateInfo(date: Date) {
        setRecordRoutine(date.date)
        setReviewExist(date.date)
        _currentDate.emit(date)
        _dateList.value = dateList.value.map {
            if(it == date) {
                it.copy(isSelected = true)
            }else {
                it.copy(isSelected = false)
            }
        }
    }

    override fun onDateSelectorClick(date: Date) {
        viewModelScope.launch {
            setDateInfo(date)
        }

    }

    private suspend fun setRecordRoutine(date: Int) {
        _currentRoutineList.emit((getRoutinesByDateUseCase(date)))
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
        val review = Review(date.date, reviewEditText.value)
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
}