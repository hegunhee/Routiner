package com.hegunhee.feature.daily.insert

sealed class InsertRoutineNavigationAction {

    object DismissDialog : InsertRoutineNavigationAction()

    object InsertCategoryDialog : InsertRoutineNavigationAction()
}
