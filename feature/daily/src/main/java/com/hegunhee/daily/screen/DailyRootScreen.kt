package com.hegunhee.daily.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.daily.R

@Composable
fun DailyRootScreen(
    viewModel: DailyViewModel = hiltViewModel(),
    onClickDrawerButton: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    DailyScreen(
        uiState = uiState,
        onClickDrawerButton = onClickDrawerButton
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyScreen(
    uiState: DailyUiState,
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

        Spacer(modifier = modifier.weight(1f))

        FloatingActionButton(
            onClick = {},
            modifier = modifier
                .align(Alignment.End)
                .padding(end = 20.dp, bottom = 20.dp)
        ) {
            Icon(imageVector = Icons.Sharp.Add, contentDescription = "addButton")
        }

    }

}

@Preview
@Composable
private fun DailyScreenPreview() {
    DailyScreen(
        uiState = DailyUiState.Init,
        onClickDrawerButton = {}
    )
}
