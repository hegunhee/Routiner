package com.hegunhee.feature.repeat

import androidx.lifecycle.*
import com.example.domain.model.Category
import com.example.domain.model.RepeatRoutine
import com.example.domain.model.Routine
import com.example.domain.usecase.category.InsertCategoryUseCase
import com.example.domain.usecase.repeatRoutine.DeleteRepeatRoutineUseCase
import com.example.domain.usecase.repeatRoutine.GetAllRepeatRoutineByFlowUseCase
import com.example.domain.usecase.repeatRoutine.InsertRepeatRoutineUseCase
import com.example.domain.usecase.routine.InsertDailyRoutineUseCase
import com.hegunhee.feature.util.getTodayDate
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
): ViewModel(), RepeatActionHandler {

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

//    init {
//        setCategory()
//    }
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
//        setCategory()
    }

    override fun openInsertRepeatRoutineDialog() {
        TODO("Not yet implemented")
    }

    override fun clickRepeatRoutine() {
        TODO("Not yet implemented")
    }


//    private fun setCategory() = viewModelScope.launch(Dispatchers.IO) {
//        _categoryList.postValue(getAllCategoryListUseCase())
//    }


}