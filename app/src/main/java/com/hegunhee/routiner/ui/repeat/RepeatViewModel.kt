package com.hegunhee.routiner.ui.repeat

import androidx.lifecycle.*
import com.hegunhee.routiner.data.entity.Category
import com.hegunhee.routiner.data.entity.RepeatRoutine
import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.domain.*
import com.hegunhee.routiner.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepeatViewModel @Inject constructor(
    private val insertRepeatRoutineUseCase: InsertRepeatRoutineUseCase,
    private val getAllRepeatRoutineByFlowUseCase: GetAllRepeatRoutineByFlowUseCase,
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase,
    private val deleteRepeatRoutineUseCase: DeleteRepeatRoutineUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase,
    private val getAllCategoryListUseCase: GetAllCategoryListUseCase
): ViewModel() {

    val repeatRoutineListLiveData : LiveData<List<RepeatRoutine>> = getAllRepeatRoutineByFlowUseCase().asLiveData()

    val isRepeatRoutineListEmpty : LiveData<Boolean> = Transformations.map(repeatRoutineListLiveData){
        it.isEmpty()
    }
    private var _clickEvent : MutableLiveData<ClickEvent> = MutableLiveData<ClickEvent>(ClickEvent.Uninitalized)
    val clickEvent
    get() = _clickEvent

    private var _categoryList : MutableLiveData<List<Category>> = MutableLiveData(listOf())
    val categoryList : LiveData<List<Category>>
        get() = _categoryList

    init {
        setCategory()
    }
    fun clickFloatingActionButton() {
        _clickEvent.value = ClickEvent.Clicked
    }

    fun finishClick() {
        _clickEvent.value = ClickEvent.Finished
    }

    fun insertDailyRoutine(text : String,category : String = "") = viewModelScope.launch(Dispatchers.IO) {
        insertDailyRoutineUseCase(Routine(getTodayDate(),text, category = category))
    }

    fun insertRepeatRoutine(text : String, dayOfWeeks : List<String>,category : String = "")= viewModelScope.launch(Dispatchers.IO){
        insertRepeatRoutineUseCase(RepeatRoutine(text,dayOfWeeks,category = category))
    }

    fun deleteRepeatRoutine(text : String) = viewModelScope.launch(Dispatchers.IO){
        deleteRepeatRoutineUseCase(text)
    }


    fun insertCategory(category : String) = viewModelScope.launch(Dispatchers.IO) {
        insertCategoryUseCase(Category(category))
    }


    fun setCategory() = viewModelScope.launch(Dispatchers.IO) {
        _categoryList.postValue(getAllCategoryListUseCase())

    }


}