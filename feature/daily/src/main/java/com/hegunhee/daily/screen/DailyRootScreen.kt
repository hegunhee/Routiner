package com.hegunhee.daily.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.daily.R

@Composable
fun DailyRootScreen(
    viewModel: DailyViewModel = hiltViewModel(),
    onClickDrawerButton: () -> Unit,
    onClickAddRoutine: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    DailyScreen(
        uiState = uiState,
        onClickDrawerButton = onClickDrawerButton,
        onClickAddRoutine = onClickAddRoutine,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyScreen(
    uiState: DailyUiState,
    onClickDrawerButton: () -> Unit,
    onClickAddRoutine: () -> Unit,
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

        when (uiState) {
            DailyUiState.Init -> {}
            DailyUiState.Empty -> {
                DailyEmptyScreen(
                    onClickAddRoutine = onClickAddRoutine
                )
            }

            is DailyUiState.Items -> {
            }
        }

        Spacer(modifier = modifier.weight(1f))

        FloatingActionButton(
            onClick = onClickAddRoutine,
            modifier = modifier
                .align(Alignment.End)
                .padding(end = 20.dp, bottom = 20.dp)
        ) {
            Icon(imageVector = Icons.Sharp.Add, contentDescription = "addButton")
        }

    }

}

@Composable
private fun ColumnScope.DailyEmptyScreen(
    onClickAddRoutine: () -> Unit,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.weight(1f))

    Text(
        text = stringResource(R.string.empty_string),
        modifier = modifier
            .align(Alignment.CenterHorizontally),
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

    Button(
        onClick = onClickAddRoutine,
        modifier = modifier.align(Alignment.CenterHorizontally),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray
        )
    ) {
        Text(stringResource(R.string.add_routine_string))
    }
}

@Preview
@Composable
private fun DailyScreenPreview() {
    DailyScreen(
        uiState = DailyUiState.Init,
        onClickDrawerButton = {},
        onClickAddRoutine = {},
    )
}
