package com.hegunhee.daily

import hegunhee.routiner.model.Routine

interface DailyActionHandler {

    fun deleteRoutine(routineNum : Int)

    fun toggleFinishRoutine(routine : Routine)

    fun onClickRoutineInsert()
}