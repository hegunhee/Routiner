package com.hegunhee.setting.screen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hegunhee.setting.screen.SettingRootScreen

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
