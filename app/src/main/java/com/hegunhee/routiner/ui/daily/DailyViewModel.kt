package com.hegunhee.routiner.ui.daily

import androidx.lifecycle.*
import com.hegunhee.routiner.data.entity.Category
import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.domain.*
import com.hegunhee.routiner.util.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getAllDailyRoutineByFlowUseCase: GetAllDailyRoutineByFlowUseCase,
    private val insertDailyRoutineUseCase: InsertDailyRoutineUseCase,
    private val deleteRoutineUseCase: DeleteRoutineUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase,
    private val getAllCategoryListUseCase: GetAllCategoryListUseCase
) : ViewModel() {

    val dailyRoutineListLiveData: LiveData<List<Routine>> = getAllDailyRoutineByFlowUseCase(getTodayDate()).asLiveData()

    val recyclerViewVisible: LiveData<Boolean> = Transformations.map(dailyRoutineListLiveData) { dailyRoutineListLiveData.value?.isNotEmpty() }

    private var _categoryList : MutableLiveData<List<Category>> = MutableLiveData(listOf())
    val categoryList : LiveData<List<Category>>
    get() = _categoryList

    private var _onClickEvent : MutableLiveData<Event> = MutableLiveData(Event.Uninitalized)
    val onClickEvent : LiveData<Event>
    get() = _onClickEvent

    val dailyRoutineProgress : LiveData<String> = Transformations.map(dailyRoutineListLiveData){
        return@map if(it.isEmpty()){
            "0 / 0"
        }else{
            "${it.count { it.isFinished }} / ${it.size}"
        }
    }

    init {
        setCategory()
    }
    fun onClickRoutineInsert() = viewModelScope.launch{
        _onClickEvent.postValue(Event.Clicked)
    }

    fun insertRoutine(text : String,category : String = "") = viewModelScope.launch(Dispatchers.IO) {
        val isExistSameText : Boolean = dailyRoutineListLiveData.value?.filter { it.text == text }?.size != 0
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
    }


    fun setCategory() = viewModelScope.launch(Dispatchers.IO) {
        _categoryList.postValue(getAllCategoryListUseCase())

    }



}