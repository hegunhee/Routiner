package com.hegunhee.routiner.insertRoutine.screen.repeat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.routiner.insertRoutine.R
import com.hegunhee.routiner.insertRoutine.screen.common.CategoryDescriptionScreen
import com.hegunhee.routiner.insertRoutine.screen.common.CategoryLazyList
import com.hegunhee.routiner.insertRoutine.screen.common.CategoryTextEnterScreen
import com.hegunhee.routiner.insertRoutine.screen.common.DayOfWeekDescriptionScreen
import com.hegunhee.routiner.insertRoutine.screen.common.RoutineTextEnterScreen
import hegunhee.routiner.model.DayOfWeek
import hegunhee.routiner.ui.item.SelectableDayOfWeek

@Composable
fun InsertRepeatRoutineRootScreen(
    viewModel: InsertRepeatRoutineViewModel = hiltViewModel(),
    onClickBackButton: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val (routineText, onRoutineTextChanged) = remember { mutableStateOf("") }
    val (addedCategoryText, onAddCategoryTextChanged) = remember { mutableStateOf("") }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    InsertRepeatRoutineScreen(
        uiState = uiState,
        routineText = routineText,
        addedCategoryText = addedCategoryText,
        onRoutineTextChanged = onRoutineTextChanged,
        onAddCategoryTextChanged = onAddCategoryTextChanged,
        onClickRoutineInsert = viewModel::onClickRoutineInsert,
        onClickCategory = viewModel::onClickCategory,
        onClickCategoryDelete = viewModel::onClickCategoryDelete,
        onClickCategoryInsert = viewModel::onClickCategoryInsert,
        onClickDayOfWeek = viewModel::onClickDayOfWeek,
        onClickBackButton = onClickBackButton,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertRepeatRoutineScreen(
    uiState: InsertRepeatRoutineUiState,
    routineText: String,
    addedCategoryText: String,
    onRoutineTextChanged: (String) -> Unit,
    onAddCategoryTextChanged: (String) -> Unit,
    onClickRoutineInsert: (String, List<String>) -> Unit,
    onClickCategory: (String) -> Unit,
    onClickCategoryDelete: (String) -> Unit,
    onClickCategoryInsert: (String) -> Unit,
    onClickDayOfWeek: (String) -> Unit,
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier,
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

        val bottomModifier = modifier.padding(top = 10.dp, start = 10.dp)

        RoutineTextEnterScreen(
            routineText = routineText,
            onRoutineTextChanged = onRoutineTextChanged,
            modifier = bottomModifier,
        )

        CategoryTextEnterScreen(
            addedCategoryText = addedCategoryText,
            onAddCategoryTextChanged = onAddCategoryTextChanged,
            onClickCategoryInsert = onClickCategoryInsert,
            modifier = bottomModifier
        )

        Row {
            CategoryDescriptionScreen(bottomModifier.alignByBaseline())
        }

        when (uiState) {
            InsertRepeatRoutineUiState.Init -> {}
            is InsertRepeatRoutineUiState.Categories -> {

                CategoryLazyList(
                    items = uiState.categories,
                    onClickCategory = onClickCategory,
                    onClickCategoryDelete = onClickCategoryDelete,
                    modifier = modifier,
                )

                Row {
                    DayOfWeekDescriptionScreen(bottomModifier.alignByBaseline())
                }

                val dayOfWeekModifier = modifier
                    .height(60.dp)
                    .background(Color.Gray)
                    .wrapContentHeight()

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(uiState.dayOfWeeks) {
                        SelectableDayOfWeek(
                            it.date,
                            it.isSelected,
                            onClickDayOfWeek = onClickDayOfWeek,
                            modifier = dayOfWeekModifier,
                        )
                    }
                }


                Spacer(modifier = modifier.weight(1f))

                Button(
                    {
                        onClickRoutineInsert(
                            routineText,
                            uiState.dayOfWeeks.filter { it.isSelected }.map { it.date })
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                    shape = RectangleShape
                ) {
                    Text(stringResource(R.string.submit_routine))
                }
            }
        }
    }
}

@Preview
@Composable
private fun InsertRepeatRoutineScreenPreview(

) {
    InsertRepeatRoutineScreen(
        uiState = InsertRepeatRoutineUiState.Categories(
            categories = listOf(),
            dayOfWeeks = listOf(DayOfWeek("월", true))
        ),
        routineText = "",
        addedCategoryText = "",
        onRoutineTextChanged = {},
        onAddCategoryTextChanged = {},
        onClickRoutineInsert = { _, _ -> },
        onClickCategory = {},
        onClickBackButton = {},
        onClickCategoryInsert = {},
        onClickCategoryDelete = {},
        onClickDayOfWeek = {}
    )
}