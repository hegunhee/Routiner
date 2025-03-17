package com.hegunhee.routiner.insertRoutine.screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.routiner.insertRoutine.screen.InsertRoutineRootScreen

const val INSERT_ROUTINE_ROUTE = "INSERT_ROUTINE"

fun NavController.navigateInsertRoutine() {
    navigate(INSERT_ROUTINE_ROUTE)
}

fun NavGraphBuilder.insertNavGraph(
    onClickBackButton : () -> Unit
) {
    composable(INSERT_ROUTINE_ROUTE) {
        InsertRoutineRootScreen(
            onClickBackButton
        )
    }
}

