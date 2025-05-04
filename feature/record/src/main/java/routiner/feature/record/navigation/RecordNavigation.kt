package routiner.feature.record.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import routiner.feature.record.RecordRootScreen

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
