package com.hegunhee.routiner.insertRoutine.repeat


sealed interface InsertRepeatRoutineUiEvent {

    data object RoutineNameEmpty : InsertRepeatRoutineUiEvent

    data object InsertCategoryNameEmpty : InsertRepeatRoutineUiEvent

    data object InsertDayOfWeekEmpty : InsertRepeatRoutineUiEvent

    data object InsertSuccess : InsertRepeatRoutineUiEvent

}
