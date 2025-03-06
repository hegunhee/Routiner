package com.example.main.screen.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.main.screen.MainRootScreen

const val MAIN_ROUTE = "MAIN"

fun NavGraphBuilder.mainNavGraph(
    onNavigateDailyScreen : () -> Unit,
) {
    composable(route = MAIN_ROUTE) {
        MainRootScreen(
            onNavigateDailyScreen = onNavigateDailyScreen,
        )
    }
}