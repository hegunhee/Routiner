package com.hegunhee.routiner.insertRoutine.repeat

import hegunhee.routiner.model.Category
import hegunhee.routiner.model.DayOfWeek

sealed interface InsertRepeatRoutineUiState {

    data object Init : InsertRepeatRoutineUiState

    data class Categories(
        val categories: List<Category>,
        val dayOfWeeks : List<DayOfWeek>,
    ) : InsertRepeatRoutineUiState

}
