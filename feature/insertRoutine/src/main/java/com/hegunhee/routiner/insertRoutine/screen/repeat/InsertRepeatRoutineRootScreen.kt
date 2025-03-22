package com.hegunhee.routiner.insertRoutine.screen.repeat

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hegunhee.routiner.insertRoutine.R
import com.hegunhee.routiner.insertRoutine.screen.InsertRoutineRootScreen

@Composable
fun InsertRepeatRoutineRootScreen(
    onClickBackButton: () -> Unit,
) {
    InsertRepeatRoutineScreen(
        onClickBackButton = onClickBackButton
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertRepeatRoutineScreen(
    onClickBackButton: () -> Unit,
) {
    Column {
        TopAppBar(
            title = {
                Text(stringResource(R.string.repeat_routine_add_desc))
            },
            navigationIcon = {
                IconButton(
                    onClickBackButton
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_desc)
                    )
                }
            }
        )
    }
}

    @Preview
    @Composable
    private fun InsertRepeatRoutineScreenPreview(

    ) {
        InsertRoutineRootScreen(
            onClickBackButton = {},
        )
    }