package com.hegunhee.routiner.insertRoutine.screen.daily

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.hegunhee.routiner.insertRoutine.R
import com.hegunhee.routiner.insertRoutine.screen.common.RoutineTextEnterScreen
import hegunhee.routiner.ui.item.SelectableCategory
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun InsertRoutineRootScreen(
    viewModel: InsertRoutineViewModel = hiltViewModel(),
    onClickBackButton: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val (routineText, onRoutineTextChanged) = remember { mutableStateOf("") }
    val (addedCategoryText, onAddCategoryTextChanged) = remember { mutableStateOf("") }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    InsertRoutineScreen(
        uiState = uiState,
        routineText = routineText,
        addedCategoryText = addedCategoryText,
        onRoutineTextChanged = onRoutineTextChanged,
        onAddCategoryTextChanged = onAddCategoryTextChanged,
        onClickRoutineInsert = viewModel::onClickRoutineInsert,
        onClickCategory = viewModel::onClickCategory,
        onClickCategoryDelete = viewModel::onClickCategoryDelete,
        onClickCategoryInsert = viewModel::onClickCategoryInsert,
        onClickBackButton = onClickBackButton,
    )

    HandleUiEvent(
        uiEvent = viewModel.uiEvent,
        context = context,
        lifecycleOwner = lifecycleOwner,
        onClickBackButton = onClickBackButton
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertRoutineScreen(
    uiState: InsertRoutineUiState,
    routineText: String,
    addedCategoryText: String,
    onRoutineTextChanged: (String) -> Unit,
    onAddCategoryTextChanged: (String) -> Unit,
    onClickRoutineInsert : (String) -> Unit,
    onClickCategory : (String) -> Unit,
    onClickCategoryDelete : (String) -> Unit,
    onClickCategoryInsert : (String) -> Unit,
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier,
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

        val bottomModifier = modifier.padding(top = 10.dp, start = 10.dp)

        RoutineTextEnterScreen(
            routineText = routineText,
            onRoutineTextChanged = onRoutineTextChanged,
            modifier = bottomModifier,
        )

        Text(
            stringResource(R.string.please_category_add_short),
            modifier = bottomModifier
        )
        NormalTextField(
            textValue = addedCategoryText,
            hintResId = R.string.please_category_add_long,
            onValueChanged = onAddCategoryTextChanged,
            modifier = bottomModifier.testTag(stringResource(R.string.add_category_text_test_tag)),
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

        Row {
            Text(
                stringResource(R.string.category_select),
                modifier = bottomModifier.alignByBaseline(),
            )
            Text(
                stringResource(R.string.select),
                modifier = bottomModifier.alignByBaseline(),
                fontSize = 15.sp,
            )
        }

        when(uiState) {
            InsertRoutineUiState.Init -> { }
            is InsertRoutineUiState.Categories -> {

                val categoryModifier = modifier
                    .height(60.dp)
                    .background(Color.Gray)
                    .wrapContentHeight()

                LazyHorizontalGrid(
                    rows = GridCells.Fixed(3),
                    modifier = modifier.height(180.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(uiState.items) {
                        SelectableCategory(
                            categoryText = it.name,
                            isCategoryClicked = it.isSelected,
                            onCategoryClick = onClickCategory,
                            onCategoryDeleteClick = onClickCategoryDelete,
                            modifier = categoryModifier
                        )
                    }
                }
            }
        }

        Spacer(modifier = modifier.weight(1f))

        Button(
            { onClickRoutineInsert(routineText)},
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
            shape = RectangleShape
        ) {
            Text(stringResource(R.string.submit_routine))
        }
    }
}

@Composable
fun NormalTextField(
    textValue: String,
    hintResId: Int,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        textValue,
        onValueChange = onValueChanged,
        singleLine = true,
        placeholder = {
            Text(stringResource(hintResId), color = Color.Gray)
        },
        textStyle = TextStyle(color = Color.Black),
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
        ),
        modifier = modifier
            .padding(end = 10.dp)
            .fillMaxWidth(),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon

    )
}

@Composable
private fun HandleUiEvent(
    uiEvent: SharedFlow<InsertRoutineUiEvent>,
    context : Context,
    lifecycleOwner: LifecycleOwner,
    onClickBackButton : () -> Unit,
) {
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            uiEvent.collect {
                when (it) {
                    InsertRoutineUiEvent.RoutineNameEmpty ->  {
                        Toast.makeText(context, context.getText(R.string.routine_name_empty).toString(), Toast.LENGTH_SHORT).show()
                    }
                    InsertRoutineUiEvent.InsertCategoryNameEmpty -> {
                        Toast.makeText(context, context.getText(R.string.add_category_empty).toString(), Toast.LENGTH_SHORT).show()
                    }
                    InsertRoutineUiEvent.InsertSuccess -> {
                        onClickBackButton()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun InsertRoutineScreenPreview() {
    InsertRoutineScreen(
        uiState = InsertRoutineUiState.Init,
        routineText = "",
        addedCategoryText = "",
        onRoutineTextChanged = {},
        onAddCategoryTextChanged = {},
        onClickRoutineInsert = {},
        onClickCategory = {},
        onClickBackButton = {},
        onClickCategoryInsert = {},
        onClickCategoryDelete = {}
    )
}
