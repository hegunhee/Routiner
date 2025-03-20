package com.hegunhee.record.screen

import android.icu.text.CaseMap.Title
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
import com.hegunhee.record.R

@Composable
fun RecordRootScreen(
    onClickDrawer : () -> Unit
) {
    RecordScreen(
        onClickDrawer = onClickDrawer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordScreen(
    date: String = "2025년 2월 26일",
    onClickDrawer: () -> Unit,
    modifier : Modifier = Modifier,
) {
    Column {
        TopAppBar(
            title = { Text(date) },
            navigationIcon = {
                IconButton(
                    onClickDrawer
                ) {
                    Icon(imageVector = Icons.Rounded.Menu, contentDescription = stringResource(R.string.menu_content_description))
                }
            }
        )
    }
}

@Preview
@Composable
private fun RecordScreenPreview() {
    RecordScreen(
        onClickDrawer = {}
    )
}
