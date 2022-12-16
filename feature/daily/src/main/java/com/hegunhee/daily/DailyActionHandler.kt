package com.hegunhee.daily

import com.example.domain.model.Routine

interface DailyActionHandler {

    fun deleteRoutine(routineNum : Int)

    fun toggleFinishRoutine(routine : Routine)

    fun onClickRoutineInsert()
}