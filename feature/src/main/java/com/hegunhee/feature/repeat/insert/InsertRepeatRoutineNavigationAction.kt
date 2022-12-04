package com.hegunhee.feature.repeat.insert

sealed class InsertRepeatRoutineNavigationAction {

    object DisMissDialog : InsertRepeatRoutineNavigationAction()

    object OpenInsertCategoryDialog : InsertRepeatRoutineNavigationAction()
}