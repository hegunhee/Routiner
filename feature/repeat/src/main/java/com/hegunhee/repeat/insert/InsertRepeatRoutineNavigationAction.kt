package com.hegunhee.repeat.insert

sealed class InsertRepeatRoutineNavigationAction {

    object DisMissDialog : InsertRepeatRoutineNavigationAction()

    object OpenInsertCategoryDialog : InsertRepeatRoutineNavigationAction()
}