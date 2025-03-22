package com.hegunhee.routiner.insertRoutine.screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.routiner.insertRoutine.screen.daily.InsertRoutineRootScreen
import com.hegunhee.routiner.insertRoutine.screen.repeat.InsertRepeatRoutineRootScreen

const val INSERT_ROUTINE_ROUTE = "INSERT_ROUTINE"

const val INSERT_REPEAT_ROUTINE_ROUTE = "INSERT_REPEAT_ROUTINE"

fun NavController.navigateInsertRoutine() {
    navigate(INSERT_ROUTINE_ROUTE)
}

fun NavController.navigateInsertRepeatRoutine() {
    navigate(INSERT_REPEAT_ROUTINE_ROUTE)
}

fun NavGraphBuilder.insertNavGraph(
    onClickBackButton: () -> Unit
) {
    composable(INSERT_ROUTINE_ROUTE) {
        InsertRoutineRootScreen(
            onClickBackButton = onClickBackButton
        )
    }
    composable(INSERT_REPEAT_ROUTINE_ROUTE) {
        InsertRepeatRoutineRootScreen(
            onClickBackButton = onClickBackButton
        )
    }
}
