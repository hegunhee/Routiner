package com.hegunhee.repeat.screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.repeat.screen.RepeatRoutineRootScreen

const val REPEAT_ROUTINE_ROUTE = "REPEAT_ROUTINE"

fun NavGraphBuilder.repeatRoutineNavGraph(
    onClickDrawer : () -> Unit,
) {
    composable(REPEAT_ROUTINE_ROUTE) {
        RepeatRoutineRootScreen(
            onClickDrawer = onClickDrawer
        )
    }
}
