package com.hegunhee.routiner.insertRoutine

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.hegunhee.routiner.insertRoutine.daily.InsertRoutineScreen
import com.hegunhee.routiner.insertRoutine.daily.InsertRoutineUiState
import hegunhee.routiner.model.Category
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InsertRoutineScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var context: Context

    @Before
    fun initContext() {
        context = composeTestRule.activity.baseContext
    }

    @Test
    fun givenRoutineText_whenChangeRoutineText_shownRoutineText() {
        composeTestRule.setContent {
            var routineText by remember { mutableStateOf("") }
            ShowInsertRoutineScreen(
                createInitUiState(),
                routineText = routineText,
                addedCategoryText = "",
                onRoutineTextChanged = { newText -> routineText = newText}
            )
        }

        val afterRoutineText = "Hello Routine Text"

        composeTestRule
            .onNodeWithText(context.getString(R.string.please_routine_enter_long))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(context.getString(R.string.routine_text_test_tag))
            .performTextInput(afterRoutineText)

        composeTestRule
            .onNodeWithTag(context.getString(R.string.routine_text_test_tag))
            .assert(hasText(afterRoutineText))
    }

    @Test
    fun givenAddCategoryText_whenChangeAddCategoryText_shownAddCategoryText() {
        composeTestRule.setContent {
            var addedCategoryText by remember { mutableStateOf("") }
            ShowInsertRoutineScreen(
                createInitUiState(),
                routineText = "",
                addedCategoryText = addedCategoryText,
                onAddCategoryTextChanged = { newText -> addedCategoryText = newText}
            )
        }

        val afterAddedCategoryText = "Hello Added Category Text"

        composeTestRule
            .onNodeWithText(context.getString(R.string.please_category_add_long))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(context.getString(R.string.add_category_text_test_tag))
            .performTextInput(afterAddedCategoryText)

        composeTestRule
            .onNodeWithTag(context.getString(R.string.add_category_text_test_tag))
            .assert(hasText(afterAddedCategoryText))
    }

    @Test
    fun givenAddCategoryText_whenAddCategory_shownCategory() {
        val categoryText = "Hello"
        composeTestRule.setContent {
            var addedCategoryText by remember { mutableStateOf(categoryText) }
            val categories = remember { mutableStateOf(listOf<Category>()) }

            val onClickAddCategory : (String) -> Unit= { categoryText ->
                categories.value += Category(categoryText)
                addedCategoryText = ""
            }

            ShowInsertRoutineScreen(
                uiState = InsertRoutineUiState.Categories(categories.value),
                routineText = "",
                addedCategoryText = addedCategoryText,
                onClickCategoryInsert = onClickAddCategory
            )
        }

        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.add_category_content_description))
            .performClick()

        composeTestRule
            .onNodeWithText(categoryText)
            .assertIsDisplayed()
    }

    @Test
    fun givenCategories_whenClickCategory_shownCheckIcon() {
        val selectedCategoryText = "운동"
        composeTestRule.setContent {
            val categories = remember { mutableStateOf(listOf(Category(selectedCategoryText),Category("공부"))) }

            val onClickCategory : (String) -> Unit= { categoryText ->
                categories.value = categories.value.map { category ->
                    if(categoryText == category.name) category.copy(isSelected = !category.isSelected)
                    else category
                }
            }

            ShowInsertRoutineScreen(
                uiState = createCategoriesUiState(categories.value),
                routineText = "",
                addedCategoryText = "",
                onClickCategory = onClickCategory,
            )
        }

        composeTestRule
            .onNodeWithText(selectedCategoryText)
            .performClick()

        composeTestRule
            .onNodeWithContentDescription(context.getString(hegunhee.routiner.ui.R.string.selected_category))
            .assertIsDisplayed()
    }

    @Composable
    private fun ShowInsertRoutineScreen(
        uiState: InsertRoutineUiState,
        routineText: String,
        addedCategoryText: String,
        onRoutineTextChanged: (String) -> Unit = {},
        onAddCategoryTextChanged: (String) -> Unit = {},
        onClickRoutineInsert: (String) -> Unit = {},
        onClickCategory: (String) -> Unit = {},
        onClickCategoryDelete: (String) -> Unit = {},
        onClickCategoryInsert: (String) -> Unit = {},
        onClickBackButton: () -> Unit = {},
        modifier: Modifier = Modifier,
    ) {
        InsertRoutineScreen(
            uiState = uiState,
            routineText = routineText,
            addedCategoryText = addedCategoryText,
            onRoutineTextChanged = onRoutineTextChanged,
            onAddCategoryTextChanged = onAddCategoryTextChanged,
            onClickRoutineInsert = onClickRoutineInsert,
            onClickCategory = onClickCategory,
            onClickCategoryDelete = onClickCategoryDelete,
            onClickCategoryInsert = onClickCategoryInsert,
            onClickBackButton = onClickBackButton,
            modifier = modifier
        )

    }

    private fun createInitUiState(): InsertRoutineUiState {
        return InsertRoutineUiState.Init
    }

    private fun createCategoriesUiState(items: List<Category>): InsertRoutineUiState {
        return InsertRoutineUiState.Categories(items)
    }
}

