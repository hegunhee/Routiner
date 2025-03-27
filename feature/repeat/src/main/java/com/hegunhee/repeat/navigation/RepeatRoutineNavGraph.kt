package com.hegunhee.repeat.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.repeat.RepeatRoutineRootScreen

const val REPEAT_ROUTINE_ROUTE = "REPEAT_ROUTINE"

fun NavGraphBuilder.repeatRoutineNavGraph(
    onClickDrawer: () -> Unit,
    onClickAddRepeatRoutine: () -> Unit,
) {
    composable(REPEAT_ROUTINE_ROUTE) {
        RepeatRoutineRootScreen(
            onClickDrawer = onClickDrawer,
            onClickAddRepeatRoutine = onClickAddRepeatRoutine,
        )
    }
}
