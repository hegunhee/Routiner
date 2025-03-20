package com.hegunhee.record.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecordRootScreen(
    onClickDrawer : () -> Unit
) {
    RecordScreen(
        onClickDrawer
    )
}

@Composable
fun RecordScreen(
    onClickDrawer: () -> Unit
) {
    Text("RecordScreen")
}

@Preview
@Composable
private fun RecordScreenPreview() {
    RecordScreen(
        onClickDrawer = {}
    )
}
