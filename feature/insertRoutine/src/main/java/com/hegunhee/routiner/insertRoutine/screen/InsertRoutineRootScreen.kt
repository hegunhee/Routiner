package com.hegunhee.routiner.insertRoutine.screen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.routiner.insertRoutine.R
import hegunhee.routiner.model.Category
import hegunhee.routiner.ui.item.SelectableCategory

@Composable
fun InsertRoutineRootScreen(
    onClickBackButton: () -> Unit,
) {
    val (routineText, onRoutineTextChanged) = remember { mutableStateOf("") }
    val (addedCategoryText, onAddCategoryTextChanged) = remember { mutableStateOf("") }

    InsertRoutineScreen(
        routineText = routineText,
        addedCategoryText = addedCategoryText,
        onRoutineTextChanged = onRoutineTextChanged,
        onAddCategoryTextChanged = onAddCategoryTextChanged,
        onClickBackButton = onClickBackButton,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertRoutineScreen(
    routineText: String,
    addedCategoryText: String,
    onRoutineTextChanged: (String) -> Unit,
    onAddCategoryTextChanged: (String) -> Unit,
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

        Text(
            stringResource(R.string.please_routine_enter_short),
            modifier = bottomModifier
        )
        NormalTextField(
            textValue = routineText,
            hintResId = R.string.please_routine_enter_long,
            onValueChanged = onRoutineTextChanged,
            modifier = bottomModifier
        )

        Text(
            stringResource(R.string.please_category_add_short),
            modifier = bottomModifier
        )
        NormalTextField(
            textValue = addedCategoryText,
            hintResId = R.string.please_routine_enter_long,
            onValueChanged = onAddCategoryTextChanged,
            modifier = bottomModifier,
            trailingIcon = {
                IconButton({}) {
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

        val list = listOf<Category>(Category("asd",false),Category("asdsad",true))

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
            items(list) {
                SelectableCategory(
                    categoryText = it.name,
                    isCategoryClicked = it.isSelected,
                    onCategoryClick = {},
                    onCategoryDeleteClick = {},
                    modifier = categoryModifier
                )
            }
        }

        Spacer(modifier = modifier.weight(1f))

        Button(
            {},
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
private fun NormalTextField(
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

@Preview
@Composable
fun InsertRoutineScreenPreview() {
    InsertRoutineScreen(
        routineText = "",
        addedCategoryText = "",
        onRoutineTextChanged = {},
        onAddCategoryTextChanged = {},
        onClickBackButton = {},
    )
}
