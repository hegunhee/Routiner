package routiner.feature.daily.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import routiner.feature.daily.DailyRootScreen

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
