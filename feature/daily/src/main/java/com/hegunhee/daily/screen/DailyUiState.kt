package com.hegunhee.daily.screen

import hegunhee.routiner.model.Routine

sealed interface DailyUiState {

    data object Init : DailyUiState

    data object Empty : DailyUiState

    data class Items(
        val routines: List<Routine>
    ) : DailyUiState

}
