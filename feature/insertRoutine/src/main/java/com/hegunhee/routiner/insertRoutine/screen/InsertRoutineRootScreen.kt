package com.hegunhee.routiner.insertRoutine.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InsertRoutineRootScreen(
    onClickBackButton : () -> Unit,
) {
    InsertRoutineScreen(
        onClickBackButton = onClickBackButton,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertRoutineScreen(
    onClickBackButton: () -> Unit,
) {
    Column {
        TopAppBar(
            title = {
                Text("루틴 추가")
            },
            navigationIcon = {
                IconButton(
                    onClickBackButton
                ) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "뒤로가기 버튼")
                }
            }
        )
    }
}

@Preview
@Composable
fun InsertRoutineScreenPreview() {
    InsertRoutineScreen(
        onClickBackButton = {},
    )
}
