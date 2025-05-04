package routiner.feature.setting.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import routiner.feature.setting.SettingRootScreen

const val SETTING_ROUTE = "SETTING"

fun NavGraphBuilder.settingNavGraph(
    onClickDrawer: () -> Unit,
) {
    composable(SETTING_ROUTE) {
        SettingRootScreen(
            onClickDrawer = onClickDrawer
        )
    }
}
