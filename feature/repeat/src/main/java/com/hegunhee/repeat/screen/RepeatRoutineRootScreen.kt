package com.hegunhee.repeat.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.repeat.R

@Composable
fun RepeatRoutineRootScreen(
    onClickDrawer: () -> Unit,
    onClickAddRepeatRoutine: () -> Unit
) {
    RepeatRoutineScreen(
        onClickDrawer = onClickDrawer,
        onClickAddRepeatRoutine = onClickAddRepeatRoutine,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatRoutineScreen(
    onClickDrawer: () -> Unit,
    onClickAddRepeatRoutine: () -> Unit,
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

        Spacer(modifier = modifier.weight(1f))
        Text(
            text = stringResource(R.string.empty_repeat_routine),
            modifier = modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

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
        onClickDrawer = {},
        onClickAddRepeatRoutine = {},
    )
}
