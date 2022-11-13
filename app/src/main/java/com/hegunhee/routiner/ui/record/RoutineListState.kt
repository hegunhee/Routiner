package com.hegunhee.routiner.ui.record

import com.example.domain.model.Routine

sealed class RoutineListState{
    object Uninitalized : RoutineListState()

    data class Success(val routineList : List<Routine>) : RoutineListState()
}
