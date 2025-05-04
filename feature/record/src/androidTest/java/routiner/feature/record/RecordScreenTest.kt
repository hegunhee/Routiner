package routiner.feature.record

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import routiner.core.model.Date
import routiner.core.model.Routine

class RecordScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var context: Context

    @Before
    fun initContext() {
        context = composeTestRule.activity.baseContext
    }

    @Test
    fun givenEmptyDate_whenScreening_shownEmptyDataMessage() {
        composeTestRule.setContent {
            ShowRecordScreen()
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.empty_date_desc))
            .assertIsDisplayed()
    }

    @Test
    fun givenDateList_whenScreening_shownSelectedDate() {
        val selectedDate = Date(20250320, isSelected = true)
        composeTestRule.setContent {
            ShowRecordScreen(
                dateList = listOf(Date(20250319),selectedDate),
                selectedDate = selectedDate
            )
        }

        composeTestRule
            .onNodeWithText(selectedDate.desc)
            .assertIsDisplayed()
    }

    @Test
    fun givenDateList_whenSelect_shownChangeSelectedDate() {
        val afterSelectedDate = Date(20250319)

        composeTestRule.setContent {
            var selectedDate by remember { mutableStateOf(Date(20250320, isSelected = true)) }
            var dateList by remember { mutableStateOf(listOf(afterSelectedDate, selectedDate)) }

            val onClickDate : (Date) -> Unit =  { selected ->
                selectedDate = selected
                val list = dateList
                list.map { it.copy(isSelected = false) }

                dateList = list.map {
                    if(it.date == selected.date) {
                        it.copy(isSelected = true)
                    } else {
                        it
                    }
                }
            }
            ShowRecordScreen(
                dateList = dateList,
                selectedDate = selectedDate,
                onClickDate = onClickDate
            )

        }
        composeTestRule
            .onNodeWithText("${afterSelectedDate.month}/${afterSelectedDate.dayOfMonth} (${afterSelectedDate.dayOfWeek})")
            .performClick()

        composeTestRule
            .onNodeWithText(afterSelectedDate.desc)
            .assertIsDisplayed()

    }

    @Composable
    private fun ShowRecordScreen(
        dateList: List<Date> = listOf(),
        routines: List<Routine> = listOf(),
        reviewState: ReviewState = ReviewState.Empty,
        selectedDate: Date = Date.EMPTY,
        reviewText: String = "",
        reviewDate: Int = Date.EMPTY.date,
        onClickDate: (Date) -> Unit = {},
        onReviewTextChanged: (String) -> Unit = {},
        onClickReviewSubmit: (reviewText: String, date: Int) -> Unit = {_,_ ->},
        onClickReviewRevise: (reviewText: String, date: Int) -> Unit = {_,_ ->},
        onClickReviewRemove: () -> Unit = {},
        onClickDrawer: () -> Unit = {},
    ) {
        RecordScreen(
            dateList = dateList,
            routines = routines,
            reviewState = reviewState,
            selectedDate = selectedDate,
            reviewText = reviewText,
            reviewDate = reviewDate,
            onClickDate = onClickDate,
            onReviewTextChanged = onReviewTextChanged,
            onClickReviewSubmit = onClickReviewSubmit,
            onClickReviewRevise = onClickReviewRevise,
            onClickReviewRemove = onClickReviewRemove,
            onClickDrawer = onClickDrawer,
        )
    }
}