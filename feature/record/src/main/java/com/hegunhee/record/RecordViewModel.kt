package com.hegunhee.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import routiner.core.domain.usecase.date.GetRoutineExistDateListUseCase
import routiner.core.domain.usecase.review.DeleteReviewUseCase
import routiner.core.domain.usecase.review.GetReviewOrNullByDateUseCase
import routiner.core.domain.usecase.review.InsertReviewUseCase
import routiner.core.domain.usecase.routine.GetRoutinesByDateUseCase
import com.hegunhee.routiner.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import routiner.core.model.Date
import routiner.core.model.Review
import routiner.core.model.Routine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getRoutineExistDateListUseCase: GetRoutineExistDateListUseCase,
    private val getRoutinesByDateUseCase: GetRoutinesByDateUseCase,
    private val getReviewOrNullByDateUseCase: GetReviewOrNullByDateUseCase,
    private val insertReviewUseCase: InsertReviewUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase,
): ViewModel() {

    private val _dateList : MutableStateFlow<List<Date>> = MutableStateFlow(listOf())
    val dateList : StateFlow<List<Date>> = _dateList.asStateFlow()

    val routines : StateFlow<List<Routine>> = dateList.map { dateList ->
        getRoutinesByDateUseCase(dateList.firstOrNull{it.isSelected}?.date ?: Date.EMPTY.date)
    }.stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed(5_000)
    )

    private val _reviewState : MutableStateFlow<ReviewState> = MutableStateFlow(ReviewState.Empty)
    val reviewState : StateFlow<ReviewState> = _reviewState.asStateFlow()

    init {
        viewModelScope.launch {
            val currentDateList = getRoutineExistDateListUseCase().filter { it.date != getTodayDate() }
            _dateList.value = currentDateList.mapIndexed { index, date ->
                if(index == currentDateList.size -1) date.copy(isSelected = true)
                else date
            }
            launch {
                dateList.collect {
                    val selectedDate = it.firstOrNull { it.isSelected}?.date ?: Date.EMPTY.date
                    setReviewExistState(selectedDate)
                }
            }
        }
    }

    private suspend fun setReviewExistState(date: Int) {
        getReviewOrNullByDateUseCase(date)?.let { review ->
            _reviewState.emit(ReviewState.Exist(review))
        } ?: run {
            _reviewState.emit(ReviewState.Empty)
        }
    }

    fun onClickDate(date : Date) {
        viewModelScope.launch {
            val dateList = _dateList.value.map { it.copy(isSelected = false) }

            _dateList.value = dateList.map {
                if(it.desc == date.desc) {
                    it.copy(isSelected = true)
                } else {
                    it
                }
            }
        }
    }

    fun onClickReviewSubmit(reviewText : String,reviewDate: Int) {
        viewModelScope.launch {
            insertReviewUseCase(Review(date = reviewDate, reviewText))
            _reviewState.value = ReviewState.Exist(Review(date = reviewDate, reviewText))
        }
    }

    fun onClickReviewRevise(reviewText: String, reviewDate: Int) {
        viewModelScope.launch {
            insertReviewUseCase(Review(date = reviewDate, reviewText))
            _reviewState.value = ReviewState.Revise
        }
    }

    fun onClickDeleteReview() {
        viewModelScope.launch {
            val state = reviewState.value
            if(state is ReviewState.Exist) {
                deleteReviewUseCase(state.review)
                _reviewState.value = ReviewState.Empty
            }
        }
    }

}
