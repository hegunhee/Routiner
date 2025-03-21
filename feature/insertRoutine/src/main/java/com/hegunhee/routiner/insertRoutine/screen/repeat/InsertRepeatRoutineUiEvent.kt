package com.hegunhee.routiner.insertRoutine.screen.repeat


sealed interface InsertRepeatRoutineUiEvent {

    data object RoutineNameEmpty : InsertRepeatRoutineUiEvent

    data object InsertCategoryNameEmpty : InsertRepeatRoutineUiEvent

    data object InsertDayOfWeekEmpty : InsertRepeatRoutineUiEvent

    data object InsertSuccess : InsertRepeatRoutineUiEvent

}
