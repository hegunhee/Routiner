package com.hegunhee.routiner.insertRoutine.screen.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hegunhee.routiner.insertRoutine.R
import com.hegunhee.routiner.insertRoutine.screen.daily.NormalTextField

@Composable
internal fun ColumnScope.RoutineTextEnterScreen(
    routineText : String,
    onRoutineTextChanged : (String) -> Unit,
    modifier : Modifier = Modifier,
) {
    Text(
        stringResource(R.string.please_routine_enter_short),
        modifier = modifier
    )
    NormalTextField(
        textValue = routineText,
        hintResId = R.string.please_routine_enter_long,
        onValueChanged = onRoutineTextChanged,
        modifier = modifier.testTag(stringResource(R.string.routine_text_test_tag))
    )
}

@Preview
@Composable
private fun RoutineEnterScreenPreview() {
    val (routineText, onRoutineTextChanged) = remember { mutableStateOf("") }
    Column {
        RoutineTextEnterScreen(
            routineText = routineText,
            onRoutineTextChanged = onRoutineTextChanged,
        )
    }
}