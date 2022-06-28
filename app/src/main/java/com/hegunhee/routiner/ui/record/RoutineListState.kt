package com.hegunhee.routiner.ui.record

import com.hegunhee.routiner.data.entity.Routine

sealed class RoutineListState{
    object Uninitalized : RoutineListState()

    data class Success(val routineList : List<Routine>) : RoutineListState()
}
