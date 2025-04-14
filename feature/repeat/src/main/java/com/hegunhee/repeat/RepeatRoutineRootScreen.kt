package com.hegunhee.repeat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hegunhee.routiner.ui.item.RepeatRoutineItem

@Composable
fun RepeatRoutineRootScreen(
    viewModel: RepeatRoutineViewModel = hiltViewModel(),
    onClickDrawer: () -> Unit,
    onClickAddRepeatRoutine: () -> Unit
) {
    RepeatRoutineScreen(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        onClickDrawer = onClickDrawer,
        onClickAddRepeatRoutine = onClickAddRepeatRoutine,
        onClickRepeatRoutineDelete = viewModel::onClickRepeatRoutineDelete,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RepeatRoutineScreen(
    uiState: RepeatRoutineUiState,
    onClickDrawer: () -> Unit,
    onClickAddRepeatRoutine: () -> Unit,
    onClickRepeatRoutineDelete: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        TopAppBar(
            title = {
                Text(stringResource(R.string.repeat_routine_desc))
            },
            navigationIcon = {
                IconButton(
                    onClickDrawer
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = stringResource(R.string.menu_content_description)
                    )
                }
            }
        )

        when(uiState) {
            RepeatRoutineUiState.Init -> {}
            RepeatRoutineUiState.Empty -> {
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = stringResource(R.string.empty_repeat_routine),
                    modifier = modifier
                        .align(Alignment.CenterHorizontally),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            is RepeatRoutineUiState.Success -> {
                val repeatRoutineItemModifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Gray)

                LazyColumn {
                    items(uiState.items) {
                        RepeatRoutineItem(
                            repeatRoutine = it,
                            onClickRepeatRoutineDelete = onClickRepeatRoutineDelete,
                            modifier = repeatRoutineItemModifier
                        )
                    }
                }
            }
        }

        Spacer(modifier = modifier.weight(1f))

        FloatingActionButton(
            onClickAddRepeatRoutine,
            modifier = modifier
                .align(Alignment.End)
                .padding(end = 20.dp, bottom = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Sharp.Add,
                contentDescription = stringResource(R.string.repeat_routine_add_button)
            )
        }
    }
}

@Preview
@Composable
private fun RepeatRoutineScreenPreview() {
    RepeatRoutineScreen(
        uiState = RepeatRoutineUiState.Init,
        onClickDrawer = {},
        onClickAddRepeatRoutine = {},
        onClickRepeatRoutineDelete = {},
    )
}
