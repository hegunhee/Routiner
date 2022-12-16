package com.hegunhee.repeat

import com.example.domain.model.RepeatRoutine


sealed class RepeatNavigationAction {

    data class ClickRepeatRoutine(val repeatRoutine: RepeatRoutine) : RepeatNavigationAction()

    object InsertRepeatRoutine : RepeatNavigationAction()
}
