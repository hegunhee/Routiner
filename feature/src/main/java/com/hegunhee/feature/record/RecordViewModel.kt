package com.hegunhee.feature.record

import android.util.Log
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
import kotlinx.coroutines.flow.combineTransform
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

    private var _recordIsEmpty: MutableLiveData<Boolean> = MutableLiveData(false)
    val recordIsEmpty: LiveData<Boolean>
        get() = _recordIsEmpty

    private var _currentDate: MutableLiveData<String> = MutableLiveData(DATE_INITALVALUE)
    val currentDate: LiveData<String>
        get() = _currentDate

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

    private var _review: MutableLiveData<ReviewState> = MutableLiveData(ReviewState.Uninitalized)
    val review: LiveData<ReviewState>
        get() = _review


    private var _reviewIsEmpty: MutableLiveData<Boolean> = MutableLiveData(true)
    val reviewIsEmpty: LiveData<Boolean>
        get() = _reviewIsEmpty

    val reviewText : LiveData<String> = Transformations.map(review){
        when(it){
            ReviewState.Uninitalized -> ""
            ReviewState.Empty -> ""
            is ReviewState.Success -> it.review.review
        }
    }


    val review_editText: MutableLiveData<String> = MutableLiveData("")

    init {
        initRecordDate()
    }

    private fun initRecordDate() = viewModelScope.launch(Dispatchers.IO) {
        val allDate = getAllDateUseCase()
        if (allDate.isEmpty()) {
            _recordIsEmpty.postValue(true)
        } else {
            _recordIsEmpty.postValue(false)
            setLeftData()
        }
    }

    fun setLeftData() = viewModelScope.launch(Dispatchers.IO) {
        if (currentDate.value == DATE_INITALVALUE) {
            val leftDate =
                getAllDateUseCase().map { it.date }.filter { it < getTodayDate() }.maxOrNull()
            if (leftDate == null) {
                Log.d("currentDateTest", "조회할 이전 데이터가 없습니다. INIT")
            } else {
                _currentDate.postValue(leftDate.toString())
                setRecordRoutine(leftDate)
                setReviewExist(leftDate)
            }
        } else {
            val leftDate =
                getAllDateUseCase().map { it.date }.filter { it < currentDate.value!!.toInt() }
                    .maxOrNull()
            if (leftDate == null) {
                Log.d("currentDateTest", "조회할 이전 데이터가 없습니다. ")
                // 존재하지 않습니다~
            } else {
                _currentDate.postValue(leftDate.toString())
                setRecordRoutine(leftDate)
                setReviewExist(leftDate)
            }
        }
    }

    fun setRightDate() = viewModelScope.launch(Dispatchers.IO) {
        if (currentDate.value == DATE_INITALVALUE) {
            val rightDate =
                getAllDateUseCase().map { it.date }.filter { it > getTodayDate() }.minOrNull()
            if (rightDate == null) {

            } else {
                _currentDate.postValue(rightDate.toString())
                setRecordRoutine(rightDate)
                setReviewExist(rightDate)
            }
        } else {
            val rightDate =
                getAllDateUseCase().map { it.date }.filter { it > currentDate.value!!.toInt() }
                    .minOrNull()
            if (rightDate == null) {
                // 존재하지 않습니다.
            } else {
                _currentDate.postValue(rightDate.toString())
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
                _review.postValue(ReviewState.Empty)
                _reviewIsEmpty.postValue(true)
            } else {
                _review.postValue(ReviewState.Success(it.first()))
                _reviewIsEmpty.postValue(false)
            }
        }
    }

    fun addReview() = viewModelScope.launch(Dispatchers.IO) {
        review_editText.value?.toString()?.let { review_text ->
            if (review_text.isNotBlank()) {
                val date = currentDate.value
                val review = Review(date.toInt(),review_text)
                insertReviewUseCase(review)
                _review.postValue(ReviewState.Success(review))
                _reviewIsEmpty.postValue(false)
                review_editText.postValue("")
            }
        }
    }

    fun deleteReview() = viewModelScope.launch(Dispatchers.IO){
        review.value?.let { reviewState ->
            if(reviewState is ReviewState.Success){
                deleteReviewUseCase(reviewState.review)
                _review.postValue(ReviewState.Empty)
                _reviewIsEmpty.postValue(true)
            }
        }
    }

    fun reviseReview() = viewModelScope.launch(Dispatchers.IO){
        review.value?.let { reviewState ->
            if(reviewState is ReviewState.Success){
                _reviewIsEmpty.postValue(true)
                review_editText.postValue(reviewText.value ?: "")
            }
        }
    }

    companion object {
        const val DATE_INITALVALUE = "기록이 존재하지 않습니다."
    }
}