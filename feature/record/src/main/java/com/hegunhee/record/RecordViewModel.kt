package com.hegunhee.record

import androidx.lifecycle.*
import com.example.domain.model.Review
import com.example.domain.model.Routine
import com.example.domain.usecase.date.GetAllDateUseCase
import com.example.domain.usecase.review.DeleteReviewUseCase
import com.example.domain.usecase.review.GetReviewUseCase
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
    private val getAllDateUseCase: GetAllDateUseCase,
    private val getRoutineListByDateUseCase: GetRoutineListByDateUseCase,
    private val getReviewUseCase: GetReviewUseCase,
    private val insertReviewUseCase: InsertReviewUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase

) : ViewModel() {

    private var _recordIsEmpty: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val recordIsEmpty: StateFlow<Boolean> = _recordIsEmpty.asStateFlow()

    private val _currentDate : MutableStateFlow<String> = MutableStateFlow(DATE_INITALVALUE)
    val currentDate : StateFlow<String> = _currentDate.asStateFlow()

    private var _currentRoutineList: MutableStateFlow<List<Routine>> = MutableStateFlow(emptyList())
    val currentRoutineListState: StateFlow<List<Routine>> = _currentRoutineList.asStateFlow()

    var currentRoutineProgress : StateFlow<String> = MutableStateFlow<String>("")

    private var _review: MutableStateFlow<ReviewState> = MutableStateFlow(ReviewState.Uninitalized)
    private val review: StateFlow<ReviewState> = _review.asStateFlow()


    private var _reviewIsEmpty: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val reviewIsEmpty: StateFlow<Boolean> = _reviewIsEmpty.asStateFlow()


    var reviewText : StateFlow<String> = MutableStateFlow<String>("")


    val reviewEditText: MutableStateFlow<String> = MutableStateFlow("")

    init {
        initRecordDate()
        initCombine()
    }

    private fun initRecordDate() = viewModelScope.launch(Dispatchers.IO) {
        val allDate = getAllDateUseCase()
        if (allDate.isEmpty()) {
            _recordIsEmpty.emit(true)
        } else {
            _recordIsEmpty.emit(false)
            setLeftData()
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

    fun setLeftData() = viewModelScope.launch(Dispatchers.IO) {
        if (currentDate.value == DATE_INITALVALUE) {
            val leftDate = getAllDateUseCase().map { it.date }.filter { it < getTodayDate() }.maxOrNull()
            if (leftDate != null) {
                _currentDate.emit(leftDate.toString())
                setRecordRoutine(leftDate)
                setReviewExist(leftDate)
            }
        } else {
            val leftDate = getAllDateUseCase().map { it.date }.filter { it < currentDate.value!!.toInt() }.maxOrNull()
            if (leftDate != null) {
                _currentDate.emit(leftDate.toString())
                setRecordRoutine(leftDate)
                setReviewExist(leftDate)
            }
        }
    }

    fun setRightDate() = viewModelScope.launch(Dispatchers.IO) {
        if (currentDate.value == DATE_INITALVALUE) {
            val rightDate = getAllDateUseCase().map { it.date }.filter { it > getTodayDate() }.minOrNull()
            if (rightDate != null) {
                _currentDate.emit(rightDate.toString())
                setRecordRoutine(rightDate)
                setReviewExist(rightDate)
            }
        } else {
            val rightDate = getAllDateUseCase().map { it.date }.filter { it > currentDate.value!!.toInt() }.minOrNull()
            if (rightDate != null) {
                _currentDate.emit(rightDate.toString())
                setRecordRoutine(rightDate)
                setReviewExist(rightDate)
            }
        }
    }

    private suspend fun setRecordRoutine(date: Int) {
        _currentRoutineList.emit((getRoutineListByDateUseCase(date)))
    }

    private suspend fun setReviewExist(date: Int) {
        getReviewUseCase(date).let {
            if (it.isEmpty()) {
                _review.emit(ReviewState.Empty)
                _reviewIsEmpty.emit(true)
            } else {
                _review.emit(ReviewState.Success(it.first()))
                _reviewIsEmpty.emit(false)
            }
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
        val reviewState = review.value
        if(reviewState is ReviewState.Success){
            deleteReviewUseCase(reviewState.review)
            _review.emit(ReviewState.Empty)
            _reviewIsEmpty.emit(true)
        }
    }

    fun reviseReview() = viewModelScope.launch(Dispatchers.IO){
        val reviewState = review.value
        if(reviewState is ReviewState.Success){
            _reviewIsEmpty.emit(true)
            reviewEditText.emit(reviewText.value)
        }
    }

    companion object {
        const val DATE_INITALVALUE = "기록이 존재하지 않습니다."
    }
}