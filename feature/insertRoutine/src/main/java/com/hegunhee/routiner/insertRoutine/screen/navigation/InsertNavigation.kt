package com.hegunhee.routiner.insertRoutine.screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.routiner.insertRoutine.screen.InsertRoutineRootScreen

const val INSERT_ROUTINE_ROUTE = "INSERT_ROUTINE"

fun NavGraphBuilder.insertNavGraph(
    onClickBackStack : () -> Unit
) {
    composable(INSERT_ROUTINE_ROUTE) {
        InsertRoutineRootScreen()
    }
}

