package com.hegunhee.category.insert

sealed interface InsertCategoryNavigationAction {

    object DismissDialog : InsertCategoryNavigationAction
}