package com.hegunhee.repeat.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hegunhee.repeat.R

@Composable
fun RepeatRoutineRootScreen(
    onClickDrawer: () -> Unit,
) {
    RepeatRoutineScreen(
        onClickDrawer = onClickDrawer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatRoutineScreen(
    onClickDrawer: () -> Unit,
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
    }
}

@Preview
@Composable
private fun RepeatRoutineScreenPreview() {
    RepeatRoutineScreen(
        onClickDrawer = {}
    )
}
