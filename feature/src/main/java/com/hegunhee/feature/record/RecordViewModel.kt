package com.hegunhee.feature.record

import androidx.lifecycle.*
import com.example.domain.model.Review
import com.example.domain.usecase.date.GetAllDateUseCase
import com.example.domain.usecase.review.DeleteReviewUseCase
import com.example.domain.usecase.review.GetReviewUseCase
import com.example.domain.usecase.review.InsertReviewUseCase
import com.example.domain.usecase.routine.GetRoutineListByDateUseCase
import com.hegunhee.feature.util.getTodayDate
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

    private var _currentRoutineList: MutableLiveData<RoutineListState> = MutableLiveData(RoutineListState.Uninitalized)
    val currentRoutineListState: LiveData<RoutineListState>
        get() = _currentRoutineList

    val currentRoutineProgress = Transformations.map(currentRoutineListState) {
        return@map when (it) {
            is RoutineListState.Uninitalized -> {
                ""
            }
            is RoutineListState.Success -> {
                "${it.routineList.count { it.isFinished }} / ${it.routineList.size}"
            }
        }
    }

    private var _review: MutableStateFlow<ReviewState> = MutableStateFlow(ReviewState.Uninitalized)
    val review: StateFlow<ReviewState> = _review.asStateFlow()


    private var _reviewIsEmpty: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val reviewIsEmpty: StateFlow<Boolean> = _reviewIsEmpty.asStateFlow()


    var reviewText : StateFlow<String> = MutableStateFlow<String>("")


    val review_editText: MutableStateFlow<String> = MutableStateFlow("")

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
        _currentRoutineList.postValue(RoutineListState.Success(getRoutineListByDateUseCase(date)))
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
        val reviewText = review_editText.value
        if(reviewText.isNotBlank()){
            val date = currentDate.value
            val review = Review(date.toInt(),reviewText)
            insertReviewUseCase(review)
            _review.emit(ReviewState.Success(review))
            _reviewIsEmpty.emit(false)
            review_editText.emit("")
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
            review_editText.emit(reviewText.value)
        }
    }

    companion object {
        const val DATE_INITALVALUE = "기록이 존재하지 않습니다."
    }
}