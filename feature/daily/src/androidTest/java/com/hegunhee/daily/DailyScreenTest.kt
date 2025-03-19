package com.hegunhee.daily

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hegunhee.daily.screen.DailyScreen
import com.hegunhee.daily.screen.DailyUiState
import hegunhee.routiner.model.Routine
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DailyScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var context: Context

    @Before
    fun initContext() {
        context = composeTestRule.activity.baseContext
    }

    @Test
    fun givenEmptyRoutines_whenScreening_shownEmptyScreen() {
        composeTestRule.setContent {
            ShowDailyScreen(
                uiState = DailyUiState.Empty,
            )
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.empty_string))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.add_button))
            .assertIsDisplayed()
    }

    @Test
    fun givenRoutines_whenClickRoutine_shownCheckedRoutine() {
        composeTestRule.setContent {
            val items = remember { mutableStateOf(listOf(Routine(date = 20250319,text = "운동", isFinished = false))) }

            val onClickRoutine : (Routine) -> Unit= { clickedRoutine ->
                items.value = items.value.map {  routine ->
                    if(clickedRoutine == routine) routine.copy(isFinished = !routine.isFinished)
                    else routine
                }
            }

            ShowDailyScreen(
                uiState = DailyUiState.Items(items.value),
                onClickRoutine = onClickRoutine
            )
        }

        composeTestRule.onNode(isToggleable()).assertIsOff()

        composeTestRule.onNode(isToggleable()).performClick()
        composeTestRule.onNode(isToggleable()).assertIsOn()

        composeTestRule.onNodeWithText(context.getString(R.string.routine_percent,100)).assertIsDisplayed()

    }

    @Test
    fun givenRoutines_whenDeleteButtonClick_shownDeleteRoutineIsGone() {

        val removedRoutineText = "운동"
        val removedRoutineId = 0

        composeTestRule.setContent {
            val items = remember {
                mutableStateOf(
                    listOf(
                        Routine(
                            date = 20250319,
                            text = removedRoutineText,
                            isFinished = true,
                            id = removedRoutineId
                        ),
                        Routine(
                            date = 20250319,
                            text = "공부",
                            isFinished = false,
                            id = 1
                        )
                    )
                )
            }

            val onClickRoutine : (Routine) -> Unit= { clickedRoutine ->
                items.value = items.value.map {  routine ->
                    if(clickedRoutine == routine) routine.copy(isFinished = !routine.isFinished)
                    else routine
                }
            }

            val onClickDeleteRoutine : (Int) -> Unit = { routineId ->
                items.value = items.value.filter { routine ->
                    routine.id != routineId
                }
            }

            ShowDailyScreen(
                uiState = DailyUiState.Items(items.value),
                onClickRoutine = onClickRoutine,
                onClickDeleteRoutine = onClickDeleteRoutine
            )
        }

        composeTestRule
            .onNode(hasText(context.getString(R.string.delete_routine)) and hasParent(hasText(removedRoutineText)))
            .performClick()

        composeTestRule
            .onNodeWithText(removedRoutineText)
            .assertIsNotDisplayed()

        composeTestRule
            .onNodeWithText(context.getString(R.string.routine_degree,0))
            .assertIsDisplayed()
    }

    @Composable
    private fun ShowDailyScreen(
        uiState: DailyUiState,
        onClickDrawerButton : () -> Unit = {},
        onClickAddRoutine : () -> Unit = {},
        onClickRoutine: (Routine) -> Unit = {},
        onClickDeleteRoutine : (Int) -> Unit = {},
        modifier : Modifier = Modifier,
    ) {
        DailyScreen(
            uiState = uiState,
            onClickDrawerButton = onClickDrawerButton,
            onClickAddRoutine = onClickAddRoutine,
            onClickRoutine = onClickRoutine,
            onClickDeleteRoutine = onClickDeleteRoutine,
            modifier = modifier,
        )
    }

}

