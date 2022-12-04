package com.hegunhee.feature.repeat

import com.example.domain.model.RepeatRoutine

interface RepeatActionHandler {

    fun openInsertRepeatRoutineDialog()

    fun clickRepeatRoutine(repeatRoutine: RepeatRoutine)
}