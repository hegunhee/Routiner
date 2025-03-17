package com.hegunhee.routiner.insertRoutine.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hegunhee.routiner.insertRoutine.R

@Composable
fun InsertRoutineRootScreen(
    onClickBackButton: () -> Unit,
) {
    val (routineText, onRoutineTextChanged) = remember { mutableStateOf("") }

    InsertRoutineScreen(
        routineText = routineText,
        onRoutineTextChanged = onRoutineTextChanged,
        onClickBackButton = onClickBackButton,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertRoutineScreen(
    routineText: String,
    onRoutineTextChanged: (String) -> Unit,
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

        
    }
}

@Composable
private fun NormalTextField(
    textValue: String,
    hintResId : Int,
    onValueChanged: (String) -> Unit,
    modifier : Modifier = Modifier,
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
            .fillMaxWidth()

    )
}

@Preview
@Composable
fun InsertRoutineScreenPreview() {
    InsertRoutineScreen(
        routineText = "",
        onRoutineTextChanged = {},
        onClickBackButton = {},
    )
}
