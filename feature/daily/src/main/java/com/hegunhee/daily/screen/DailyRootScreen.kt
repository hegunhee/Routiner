package com.hegunhee.daily.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hegunhee.daily.R

@Composable
fun DailyRootScreen(
    onClickDrawerButton: () -> Unit,
) {
    DailyScreen(
        onClickDrawerButton = onClickDrawerButton
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyScreen(
    onClickDrawerButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        TopAppBar(
            title = {
                Text(stringResource(R.string.daily_routine))
            },
            navigationIcon = {
                IconButton(onClickDrawerButton) {
                    Icon(imageVector = Icons.Rounded.Menu, contentDescription = "DrawerOpenButton")
                }
            }
        )
        Text("DailyScreen")
    }

}

@Preview
@Composable
private fun DailyScreenPreview() {
    DailyScreen(
        onClickDrawerButton = {}
    )
}
