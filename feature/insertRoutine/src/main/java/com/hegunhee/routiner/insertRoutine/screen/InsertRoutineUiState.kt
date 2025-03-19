package com.hegunhee.routiner.insertRoutine.screen

import hegunhee.routiner.model.Category

sealed interface InsertRoutineUiState {

    data object Init : InsertRoutineUiState

    data class Categories(
        val items: List<Category>
    ) : InsertRoutineUiState

}
