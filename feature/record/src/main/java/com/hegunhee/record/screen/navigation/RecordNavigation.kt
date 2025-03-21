package com.hegunhee.record.screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.record.screen.RecordRootScreen

const val RECORD_ROUTE = "RECORD"

fun NavGraphBuilder.recordNavGraph(
    onClickDrawer : () -> Unit
) {
    composable(RECORD_ROUTE) {
        RecordRootScreen(
            onClickDrawer = onClickDrawer
        )
    }
}
