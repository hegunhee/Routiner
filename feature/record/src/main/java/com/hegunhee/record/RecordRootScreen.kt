package com.hegunhee.record

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import hegunhee.routiner.model.Date
import hegunhee.routiner.model.Review
import hegunhee.routiner.model.Routine
import hegunhee.routiner.ui.item.DateSelector
import hegunhee.routiner.ui.item.RecordRoutine

@Composable
fun RecordRootScreen(
    viewModel: RecordViewModel = hiltViewModel(),
    onClickDrawer: () -> Unit
) {
    val (reviewText, onReviewTextChanged) = rememberSaveable { mutableStateOf("") }
    val (reviewDate, onReviewDateChanged) = rememberSaveable { mutableIntStateOf(Date.EMPTY.date) }

    val dateList = viewModel.dateList.collectAsStateWithLifecycle().value
    val selectedDate = dateList.firstOrNull{ it.isSelected } ?: Date.EMPTY

    RecordScreen(
        dateList = dateList,
        routines = viewModel.routines.collectAsStateWithLifecycle().value,
        reviewState = viewModel.reviewState.collectAsStateWithLifecycle().value,
        selectedDate = selectedDate,
        reviewText = reviewText,
        reviewDate = reviewDate,
        onClickDate = viewModel::onClickDate,
        onReviewTextChanged = onReviewTextChanged,
        onClickReviewSubmit = viewModel::onClickReviewSubmit,
        onClickReviewRevise = viewModel::onClickReviewRevise,
        onClickReviewRemove = viewModel::onClickDeleteReview,
        onClickDrawer = onClickDrawer
    )

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.reviewState) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.reviewState.collect { state ->
                when (state) {
                    is ReviewState.Exist -> {
                        onReviewTextChanged(state.review.review)
                        onReviewDateChanged(state.review.date)
                    }
                    ReviewState.Empty -> {
                        onReviewTextChanged("")
                        onReviewDateChanged(selectedDate.date)
                    }

                    else -> {

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RecordScreen(
    dateList: List<Date>,
    routines: List<Routine>,
    reviewState: ReviewState,
    selectedDate: Date,
    reviewText: String,
    reviewDate: Int,
    onClickDate: (Date) -> Unit,
    onReviewTextChanged: (String) -> Unit,
    onClickReviewSubmit: (reviewText: String, date: Int) -> Unit,
    onClickReviewRevise: (reviewText: String, date: Int) -> Unit,
    onClickReviewRemove: () -> Unit,
    onClickDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        TopAppBar(
            title = {
                if (selectedDate.desc == Date.EMPTY.desc) {
                    Text(stringResource(R.string.empty_date_desc))
                } else {
                    Text(selectedDate.desc)

                }
            },
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
    onClickReviewRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    val roundedModifier = modifier.clip(RoundedCornerShape(16.dp))
    Column(
        modifier = roundedModifier
            .background(Color.Gray)
            .fillMaxWidth()
    ) {
        when (reviewState) {
            ReviewState.Loading -> {}
            ReviewState.Empty -> {
                Text(stringResource(R.string.review_description), fontSize = 20.sp)
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
                Text(stringResource(R.string.review_description), fontSize = 20.sp)
                Text(
                    text = reviewText,
                    modifier = roundedModifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(Color.White)
                        .height(100.dp),
                    color = Color.Black
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
                        onClick = { onClickReviewRemove() },
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
                Text(stringResource(R.string.review_description), fontSize = 20.sp)
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
    val reviewDate = rememberSaveable { mutableIntStateOf(Date.EMPTY.date) }

    RecordScreen(
        dateList = listOf(),
        routines = listOf(),
        reviewState = ReviewState.Empty,
        selectedDate = Date.EMPTY,
        reviewText = reviewText,
        reviewDate = reviewDate.value,
        onClickDate = {},
        onReviewTextChanged = onReviewTextChanged,
        onClickReviewSubmit = { _, _ -> },
        onClickReviewRevise = { _, _ -> },
        onClickReviewRemove = { },
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
        onClickReviewRemove = {
            reviewText = ""
            reviewState = ReviewState.Empty
        }
    )
}
