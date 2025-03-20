package com.hegunhee.record.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.record.R
import hegunhee.routiner.model.Date
import hegunhee.routiner.model.Review
import hegunhee.routiner.model.Routine
import hegunhee.routiner.ui.item.DateSelector
import hegunhee.routiner.ui.item.RecordRoutine

@Composable
fun RecordRootScreen(
    onClickDrawer: () -> Unit
) {
    val (reviewText, onReviewTextChanged) = rememberSaveable { mutableStateOf("") }
    val (reviewDate, onReviewDateChanged) = rememberSaveable { mutableIntStateOf(-1) }

    RecordScreen(
        dateList = listOf(),
        routines = listOf(),
        reviewState = ReviewState.Empty,
        reviewText = reviewText,
        reviewDate = reviewDate,
        onClickDate = {},
        onReviewTextChanged = onReviewTextChanged,
        onClickReviewSubmit = {_, _ ->},
        onClickReviewRevise = {_, _ ->},
        onClickReviewRemove = {_, _ ->},
        onClickDrawer = onClickDrawer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordScreen(
    dateList : List<Date>,
    routines : List<Routine>,
    reviewState : ReviewState,
    selectedDate: Date = Date.EMPTY,
    reviewText : String,
    reviewDate : Int,
    onClickDate : (Date) -> Unit,
    onReviewTextChanged: (String) -> Unit,
    onClickReviewSubmit: (reviewText: String, date: Int) -> Unit,
    onClickReviewRevise: (reviewText: String, date: Int) -> Unit,
    onClickReviewRemove: (reviewText: String, date: Int) -> Unit,
    onClickDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        TopAppBar(
            title = { Text(selectedDate.desc) },
            navigationIcon = {
                IconButton(
                    onClickDrawer
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = stringResource(R.string.menu_content_description)
                    )
                }
            }
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(dateList) { date ->
                DateSelector(
                    date = date,
                    onClickDate = onClickDate
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(routines) { routine ->
                RecordRoutine(
                    routine = routine
                )
            }
        }

        Spacer(modifier = modifier.weight(1f))

        ReviewScreen(
            reviewState = reviewState,
            reviewText = reviewText,
            reviewDate = reviewDate,
            onReviewTextChanged = onReviewTextChanged,
            onClickReviewSubmit = onClickReviewSubmit,
            onClickReviewRevise = onClickReviewRevise,
            onClickReviewRemove = onClickReviewRemove,
        )
    }
}


@Composable
private fun ReviewScreen(
    reviewState: ReviewState,
    reviewText: String,
    reviewDate: Int,
    onReviewTextChanged: (String) -> Unit,
    onClickReviewSubmit: (reviewText: String, date: Int) -> Unit,
    onClickReviewRevise: (reviewText: String, date: Int) -> Unit,
    onClickReviewRemove: (reviewText: String, date: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val roundedModifier = modifier.clip(RoundedCornerShape(16.dp))
    Column(
        modifier = roundedModifier
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        Text(stringResource(R.string.review_description), fontSize = 20.sp)
        when (reviewState) {
            ReviewState.Loading -> {}
            ReviewState.Empty -> {
                OutlinedTextField(
                    value = reviewText,
                    onValueChange = onReviewTextChanged,
                    modifier = roundedModifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp),
                    textStyle = TextStyle(color = Color.Black),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    ),
                    placeholder = {
                        Text(text = stringResource(R.string.review_description))
                    }
                )
                Row {
                    Button(
                        onClick = { onClickReviewSubmit(reviewText, reviewDate) },
                        modifier = modifier
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue
                        )
                    ) {
                        Text(stringResource(R.string.submit))
                    }
                }

            }

            is ReviewState.Exist -> {
                Text(
                    text = reviewText,
                    modifier = roundedModifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(Color.White)
                        .height(100.dp)
                )
                Row {
                    Button(
                        onClick = { onClickReviewRevise(reviewText, reviewDate) },
                        modifier = modifier
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue
                        )
                    ) {
                        Text(stringResource(R.string.revise))
                    }
                    Button(
                        onClick = { onClickReviewRemove(reviewText, reviewDate) },
                        modifier = modifier
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Magenta
                        )
                    ) {
                        Text(stringResource(R.string.remove))
                    }
                }

            }

            ReviewState.Revise -> {
                OutlinedTextField(
                    value = reviewText,
                    onValueChange = onReviewTextChanged,
                    modifier = roundedModifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp),
                    textStyle = TextStyle(color = Color.Black),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    ),
                    placeholder = {
                        Text(text = stringResource(R.string.review_description))
                    }
                )
                Row {
                    Button(
                        onClick = { onClickReviewSubmit(reviewText, reviewDate) },
                        modifier = modifier
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue
                        )
                    ) {
                        Text(stringResource(R.string.submit))
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun RecordScreenPreview() {
    val (reviewText, onReviewTextChanged) = rememberSaveable { mutableStateOf("") }
    val (reviewDate, onReviewDateChanged) = rememberSaveable { mutableIntStateOf(-1) }

    RecordScreen(
        dateList = listOf(),
        routines = listOf(),
        reviewState = ReviewState.Empty,
        reviewText = reviewText,
        reviewDate = reviewDate,
        onClickDate = {},
        onReviewTextChanged = onReviewTextChanged,
        onClickReviewSubmit = { _, _ -> },
        onClickReviewRevise = { _, _ -> },
        onClickReviewRemove = { _, _ -> },
        onClickDrawer = { },
    )
}

@Preview
@Composable
private fun ReviewScreenPreview() {
    var reviewText by remember { mutableStateOf("") }
    var reviewState by remember { mutableStateOf<ReviewState>(ReviewState.Empty) }
    ReviewScreen(
        reviewState = reviewState,
        reviewText = reviewText,
        reviewDate = 20250320,
        onReviewTextChanged = { newText ->
            reviewText = newText
        },
        onClickReviewSubmit = { newText, _ ->
            if (newText.isNotBlank()) {
                reviewText = newText
                reviewState = ReviewState.Exist(Review(20250320, reviewText))
            }
        },
        onClickReviewRevise = { newText, _ ->
            reviewText = newText
            reviewState = ReviewState.Revise
        },
        onClickReviewRemove = { newText, _ ->
            reviewText = ""
            reviewState = ReviewState.Empty
        }
    )
}
