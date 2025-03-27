package com.hegunhee.record.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.record.RecordRootScreen

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
