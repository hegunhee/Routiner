package com.hegunhee.routiner.insertRoutine.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hegunhee.routiner.insertRoutine.R
import com.hegunhee.routiner.insertRoutine.daily.NormalTextField

@Composable
internal fun ColumnScope.RoutineTextEnterScreen(
    routineText: String,
    onRoutineTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
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

@Composable
internal fun ColumnScope.CategoryTextEnterScreen(
    addedCategoryText: String,
    onAddCategoryTextChanged: (String) -> Unit,
    onClickCategoryInsert: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        stringResource(R.string.please_category_add_short),
        modifier = modifier
    )
    NormalTextField(
        textValue = addedCategoryText,
        hintResId = R.string.please_category_add_long,
        onValueChanged = onAddCategoryTextChanged,
        modifier = modifier.testTag(stringResource(R.string.add_category_text_test_tag)),
        trailingIcon = {
            IconButton({ onClickCategoryInsert(addedCategoryText) }) {
                Icon(
                    imageVector = Icons.Rounded.AddCircle,
                    contentDescription = stringResource(R.string.add_category_content_description),
                    tint = Color.Blue
                )
            }
        }
    )
}

@Composable
internal fun RowScope.CategoryDescriptionScreen(
    modifier: Modifier = Modifier,
) {
    Text(
        stringResource(R.string.category_select),
        modifier = modifier,
    )
    Text(
        stringResource(R.string.select),
        modifier = modifier,
        fontSize = 15.sp,
    )
}

@Composable
internal fun RowScope.DayOfWeekDescriptionScreen(
    modifier: Modifier = Modifier,
) {
    Text(
        stringResource(R.string.select_day_of_week),
        modifier = modifier,
    )
    Text(
        stringResource(R.string.required),
        modifier = modifier,
        fontSize = 15.sp
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

@Preview
@Composable
private fun CategoryTextEnterScreenPreview() {
    val (addedCategoryText, onCategoryTextChanged) = remember { mutableStateOf("") }
    Column {
        CategoryTextEnterScreen(
            addedCategoryText = addedCategoryText,
            onAddCategoryTextChanged = onCategoryTextChanged,
            onClickCategoryInsert = {},
        )
    }
}

@Preview
@Composable
private fun CategoryDescriptionScreenPreview() {
    Row {
        CategoryDescriptionScreen(modifier = Modifier.alignByBaseline())
    }
}

@Preview
@Composable
private fun DayOfWeekDescriptionScreenPreview() {
    Row {
        DayOfWeekDescriptionScreen(modifier = Modifier.alignByBaseline())
    }
}
