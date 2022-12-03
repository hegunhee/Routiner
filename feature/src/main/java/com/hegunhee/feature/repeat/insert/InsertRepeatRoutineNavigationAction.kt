package com.hegunhee.feature.repeat.insert

import com.hegunhee.feature.repeat.RepeatNavigationAction

sealed class InsertRepeatRoutineNavigationAction {

    object DisMissDialog : InsertRepeatRoutineNavigationAction()

    object OpenInsertCategoryDialog : InsertRepeatRoutineNavigationAction()
}