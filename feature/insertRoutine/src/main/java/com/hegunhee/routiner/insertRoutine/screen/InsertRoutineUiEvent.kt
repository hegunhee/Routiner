package com.hegunhee.routiner.insertRoutine.screen

sealed interface InsertRoutineUiEvent {

    data object RoutineNameEmpty : InsertRoutineUiEvent

    data object InsertCategoryNameEmpty : InsertRoutineUiEvent

    data object InsertSuccess : InsertRoutineUiEvent

}
