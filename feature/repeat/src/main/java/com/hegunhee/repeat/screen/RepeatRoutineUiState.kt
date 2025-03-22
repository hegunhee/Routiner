package com.hegunhee.repeat.screen

import hegunhee.routiner.model.RepeatRoutine

sealed interface RepeatRoutineUiState {

    data object Init : RepeatRoutineUiState

    data object Empty : RepeatRoutineUiState

    data class Success(
        val items : List<RepeatRoutine>
    ) : RepeatRoutineUiState

}
