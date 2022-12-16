package com.hegunhee.daily.insert

sealed class InsertRoutineNavigationAction {

    object DismissDialog : InsertRoutineNavigationAction()

    object InsertCategoryDialog : InsertRoutineNavigationAction()
}
