package com.example.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.main.R
import hegunhee.routiner.designsystem.theme.DarkGreen

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
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            stringResource(R.string.app_name),
            modifier = modifier.padding(top = 100.dp, start = 30.dp),
            fontSize = 40.sp,
            color = DarkGreen
        )
        Text(
            stringResource(R.string.app_description),
            modifier = modifier.padding(top = 10.dp, start = 30.dp),
            fontSize = 20.sp,
        )
    }

}


@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen(onNavigateDailyScreen = {})
}
