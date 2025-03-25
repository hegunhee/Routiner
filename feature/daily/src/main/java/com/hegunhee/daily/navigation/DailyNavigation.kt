package com.hegunhee.daily.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.daily.DailyRootScreen

const val DAILY_ROUTE = "DAILY"

fun NavGraphBuilder.dailyNavGraph(
    onClickDrawerButton: () -> Unit,
    onClickAddRoutine: () -> Unit,
) {
    composable(DAILY_ROUTE) {
        DailyRootScreen(
            onClickDrawerButton = onClickDrawerButton,
            onClickAddRoutine = onClickAddRoutine,
        )
    }
}
