package com.hegunhee.routiner.insertRoutine.daily

sealed interface InsertRoutineUiEvent {

    data object RoutineNameEmpty : InsertRoutineUiEvent

    data object InsertCategoryNameEmpty : InsertRoutineUiEvent

    data object InsertSuccess : InsertRoutineUiEvent

}
