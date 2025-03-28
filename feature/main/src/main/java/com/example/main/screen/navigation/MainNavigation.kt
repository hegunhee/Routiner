package com.example.main.screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.main.screen.MainRootScreen

const val MAIN_ROUTE = "MAIN"

fun NavGraphBuilder.mainNavGraph(
    successRoute: String,
    onNavigateTo : (String) -> Unit,
) {
    composable(route = MAIN_ROUTE) {
        MainRootScreen(
            successRoute = successRoute,
            onNavigateDailyScreen = onNavigateTo,
        )
    }
}