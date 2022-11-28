package com.hegunhee.feature.daily

import androidx.lifecycle.*
import com.example.domain.model.Category
import com.example.domain.model.Routine
import com.example.domain.usecase.category.GetAllCategoryListUseCase
import com.example.domain.usecase.category.InsertCategoryUseCase
import com.example.domain.usecase.routine.DeleteRoutineUseCase
import com.example.domain.usecase.routine.GetAllDailyRoutineByFlowUseCase
import com.example.domain.usecase.routine.InsertDailyRoutineUseCase
import com.hegunhee.feature.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getAllDailyRoutineByFlowUseCase: GetAllDailyRoutineByFlowUseCase,
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase,
    private val deleteRoutineUseCase: DeleteRoutineUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase,
    private val getAllCategoryListUseCase: GetAllCategoryListUseCase
) : ViewModel(), DailyActionHandler {

    val dailyRoutineEntityListLiveData: LiveData<List<Routine>> = getAllDailyRoutineByFlowUseCase(
        getTodayDate()
    ).asLiveData()

    val recyclerViewVisible: LiveData<Boolean> = Transformations.map(dailyRoutineEntityListLiveData) { dailyRoutineEntityListLiveData.value?.isNotEmpty() }

    private var _categoryEntityList : MutableLiveData<List<Category>> = MutableLiveData(listOf())
    val categoryEntityList : LiveData<List<Category>>
    get() = _categoryEntityList

    private var _onClickEvent : MutableLiveData<Event> = MutableLiveData(Event.Uninitalized)
    val onClickEvent : LiveData<Event>
    get() = _onClickEvent

    val dailyRoutineProgress : LiveData<String> = Transformations.map(dailyRoutineEntityListLiveData){
        return@map if(it.isEmpty()){
            "0 / 0"
        }else{
            "${it.count { it.isFinished }} / ${it.size}"
        }
    }

    init {
        setCategory()
    }
    override fun onClickRoutineInsert() {
        viewModelScope.launch{
            _onClickEvent.postValue(Event.Clicked)
        }
    }

    fun insertRoutine(text : String,category : String = "") = viewModelScope.launch(Dispatchers.IO) {
        val isExistSameText : Boolean = dailyRoutineEntityListLiveData.value?.filter { it.text == text }?.size != 0
        if(isExistSameText){

        } else{
            insertDailyRoutineUseCase(Routine(getTodayDate(),text,category = category))
        }
    }

    fun deleteData(id : Int) = viewModelScope.launch(Dispatchers.IO) {
        deleteRoutineUseCase(id)
    }

    fun toggleFinished(routine: Routine) = viewModelScope.launch(Dispatchers.IO) {
        insertDailyRoutineUseCase(routine)
    }

    fun endClick() {
        _onClickEvent.postValue(Event.EndClick)
    }

    fun insertCategory(category : String) = viewModelScope.launch(Dispatchers.IO) {
        insertCategoryUseCase(Category(category))
        setCategory()
    }


    private fun setCategory() = viewModelScope.launch(Dispatchers.IO) {
        _categoryEntityList.postValue(getAllCategoryListUseCase())

    }

    override fun deleteRoutine(routineNum: Int) {
        viewModelScope.launch{
            deleteRoutineUseCase(routineNum)
        }
    }

    override fun toggleFinishRoutine(routine: Routine) {
        viewModelScope.launch {
            insertDailyRoutineUseCase(routine.copy(isFinished = !routine.isFinished))
        }

    }


}