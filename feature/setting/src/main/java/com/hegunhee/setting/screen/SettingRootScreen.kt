package com.hegunhee.setting.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingRootScreen(
    onClickDrawer : () -> Unit,
) {
    SettingScreen(
        onClickDrawer = onClickDrawer,
    )
}

@Composable
fun SettingScreen(
    onClickDrawer: () -> Unit,
) {
    Text("SettingScreen")
}

@Preview
@Composable
private fun SettingScreenPreview() {
    SettingScreen(
        onClickDrawer = {},
    )
}
