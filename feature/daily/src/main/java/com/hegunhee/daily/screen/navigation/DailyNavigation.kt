package com.hegunhee.daily.screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.daily.screen.DailyRootScreen

const val DAILY_ROUTE = "DAILY"

fun NavGraphBuilder.dailyNavGraph(

) {
    composable(DAILY_ROUTE) {
        DailyRootScreen()
    }
}
