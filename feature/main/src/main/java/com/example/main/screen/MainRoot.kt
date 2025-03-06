package com.example.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainRootScreen(
    onNavigateDailyScreen: () -> Unit
) {
    MainScreen(
        onNavigateDailyScreen = onNavigateDailyScreen
    )
}

@Composable
fun MainScreen(
    onNavigateDailyScreen: () -> Unit,
) {
    Column {
        Text("MainScreen입니다.")
    }
}


@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen(onNavigateDailyScreen = {})
}
