package routiner.feature.repeat

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import routiner.core.model.RepeatRoutine

class RepeatScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var context: Context

    @Before
    fun initContext() {
        context = composeTestRule.activity.baseContext
    }

    @Test
    fun givenEmptyUiState_whenScreening_shownEmptyRoutineText() {
        // Given
        composeTestRule.setContent {
            ShowRepeatScreen(
                uiState = RepeatRoutineUiState.Empty,
            )
        }

        // When & Shown
        composeTestRule
            .onNodeWithText(context.getString(R.string.empty_repeat_routine))
            .assertIsDisplayed()
    }

    @Test
    fun givenSuccessUiState_whenScreening_shownRepeatRoutines() {
        // Given
        val initList = createRepeatRoutines(3)
        composeTestRule.setContent {
            var uiState by remember {
                mutableStateOf<RepeatRoutineUiState>(
                    RepeatRoutineUiState.Success(
                        initList
                    )
                )
            }
            val onClickDeleteRepeatRoutine: (String) -> Unit = { text ->
                (uiState as? RepeatRoutineUiState.Success)?.let { currentState ->
                    uiState = currentState.copy(currentState.items.filter { it.text != text })
                }
            }

            ShowRepeatScreen(
                uiState = uiState,
                onClickRepeatRoutineDelete = onClickDeleteRepeatRoutine,
            )
        }

        composeTestRule
            .onNodeWithText(initList[0].text)
            .assertIsDisplayed()

        // When
        composeTestRule
            .onAllNodesWithContentDescription(context.getString(routiner.core.ui.R.string.repeat_routine_delete_button_desc))
            .onFirst()
            .performClick()

        // Shown
        composeTestRule
            .onNodeWithText(initList[0].text, ignoreCase = true)
            .assertDoesNotExist()
    }


    @Composable
    private fun ShowRepeatScreen(
        uiState: RepeatRoutineUiState,
        onClickDrawer: () -> Unit = {},
        onClickAddRepeatRoutine: () -> Unit = {},
        onClickRepeatRoutineDelete: (String) -> Unit = {},
        modifier: Modifier = Modifier,
    ) {
        RepeatRoutineScreen(
            uiState,
            onClickDrawer,
            onClickAddRepeatRoutine,
            onClickRepeatRoutineDelete,
            modifier,
        )
    }

    private fun createRepeatRoutines(size: Int): List<RepeatRoutine> {
        return (0..size).map { num ->
            RepeatRoutine(
                text = "Test $num",
                dayOfWeekList = listOf("월, 화"),
                category = "category $num",
            )
        }
    }

}