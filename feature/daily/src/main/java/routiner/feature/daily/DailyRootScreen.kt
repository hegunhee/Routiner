package routiner.feature.daily

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import routiner.core.model.Routine
import routiner.core.ui.component.DailyRoutine

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
internal fun DailyScreen(
    uiState: DailyUiState,
    onClickDrawerButton: () -> Unit,
    onClickAddRoutine: () -> Unit,
    onClickRoutine: (Routine) -> Unit,
    onClickDeleteRoutine: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClickAddRoutine
            ) {
                Icon(imageVector = Icons.Sharp.Add, contentDescription = stringResource(R.string.add_button))
            }
        }
    ){ values ->
        Column(
            modifier = modifier.padding(values)
        ) {
            when (uiState) {
                DailyUiState.Init -> {}
                DailyUiState.Empty -> {
                    DailyEmptyScreen(
                        onClickAddRoutine = onClickAddRoutine,
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
        }
    }

}

@Composable
private fun ColumnScope.DailyEmptyScreen(
    onClickAddRoutine: () -> Unit,
    modifier: Modifier = Modifier
) {
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

    items(items) { routine ->
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
            modifier = Modifier.padding(top = 20.dp)
        )
    }

    item {
        Text(
            stringResource(R.string.routine_percent, calculateFinishedPercent(items.size,items.count{it.isFinished})),
            modifier = Modifier.padding(start = 20.dp)
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
