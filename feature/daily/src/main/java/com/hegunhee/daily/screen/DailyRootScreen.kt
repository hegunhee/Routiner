package com.hegunhee.daily.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
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
import hegunhee.routiner.model.Routine
import hegunhee.routiner.ui.item.DailyRoutine

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
        onClickRoutine = viewModel::onClickDailyRoutine,
        onClickDeleteRoutine = viewModel::onClickDeleteDailyRoutine,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyScreen(
    uiState: DailyUiState,
    onClickDrawerButton: () -> Unit,
    onClickAddRoutine: () -> Unit,
    onClickRoutine: (Routine) -> Unit,
    onClickDeleteRoutine: (Int) -> Unit,
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
                LazyColumn {
                    dailyRoutineList(
                        uiState.routines,
                        onClickRoutine = onClickRoutine,
                        onClickDeleteRoutine = onClickDeleteRoutine,
                        modifier = modifier.padding(top = 10.dp, start = 10.dp,end = 10.dp)
                    )
                }
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

private fun LazyListScope.dailyRoutineList(
    items: List<Routine>,
    onClickRoutine: (Routine) -> Unit,
    onClickDeleteRoutine: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    item {
        Text(
            stringResource(R.string.routine_list_text),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
    }

    items(items, key = { it.text }) { routine ->
        DailyRoutine(
            routine,
            onClickRoutine = onClickRoutine,
            onClickDeleteRoutine = onClickDeleteRoutine,
        )
    }

    item {
        Text(
            stringResource(R.string.routine_degree),
            fontSize = 20.sp,
            modifier = modifier.padding(top = 20.dp)
        )
    }

    item {
        Text(
            stringResource(R.string.routine_percent, calculateFinishedPercent(items.size,items.count{it.isFinished})),
            modifier = modifier.padding(start = 20.dp)
        )
    }

}

private fun calculateFinishedPercent(
    size : Int,
    finishedCount : Int,
) : Int {
    return (finishedCount * 100/ size)
}

@Preview
@Composable
private fun DailyScreenPreview() {
    DailyScreen(
        uiState = DailyUiState.Init,
        onClickDrawerButton = {},
        onClickAddRoutine = {},
        onClickRoutine = {},
        onClickDeleteRoutine = {}
    )
}
