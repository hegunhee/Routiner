package com.example.main

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.main.screen.MainScreen
import com.example.main.screen.MainUiState
import com.example.main.screen.MainUiState.FirstOpenApp
import com.example.main.screen.MainUiState.Init
import com.example.main.screen.MainUiState.InitDate
import com.example.main.screen.MainUiState.InsertRepeatRoutine
import com.example.main.screen.MainUiState.Success
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var context : Context

    @Before
    fun initContext() {
        context = composeTestRule.activity.baseContext
    }

    @Test
    fun givenUiStateInit_whenInitScreen_shownInitMessage() {
        composeTestRule.setContent {
            ShowMainScreen(
                uiState = Init,
                onAction = {}
            )
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.init_message))
            .assertIsDisplayed()
    }

    @Test
    fun givenUiStateInit_whenWait_shownFirstAppOpenMessage() {
        composeTestRule.setContent {
            val uiState = remember { mutableStateOf<MainUiState>(Init) }

            val onAction : (MainUiState) -> Unit = { state ->
                if(state == Init) uiState.value = FirstOpenApp
            }

            ShowMainScreen(
                uiState = uiState.value,
                onAction = onAction
            )
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.first_app_open_message))
            .assertIsDisplayed()

    }

    @Test
    fun givenUiStateFirstAppOpen_whenWait_shownInitDateMessage() {
        composeTestRule.setContent {
            val uiState = remember { mutableStateOf<MainUiState>(FirstOpenApp) }

            val onAction : (MainUiState) -> Unit = { state ->
                if(state == FirstOpenApp) uiState.value = InitDate
            }

            ShowMainScreen(
                uiState = uiState.value,
                onAction = onAction
            )
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.init_date_message))
            .assertIsDisplayed()

    }

    @Test
    fun givenUiStateInitDate_whenWait_shownInsertRepeatRoutineMessage() {
        composeTestRule.setContent {
            val uiState = remember { mutableStateOf<MainUiState>(InitDate) }

            val onAction : (MainUiState) -> Unit = { state ->
                if(state == InitDate) uiState.value = InsertRepeatRoutine
            }

            ShowMainScreen(
                uiState = uiState.value,
                onAction = onAction
            )
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.insert_repeat_routine_message))
            .assertIsDisplayed()

    }

    @Test
    fun givenUiStateSuccess_when_shownSuccessMessage() {
        composeTestRule.setContent {
            ShowMainScreen(
                uiState = Success,
                onAction = {}
            )
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.success_message))
            .assertIsDisplayed()
    }

    @Composable
    private fun ShowMainScreen(uiState: MainUiState, onAction: (MainUiState) -> Unit) {
        MainScreen(
            uiState = uiState,
            successRoute = "",
            onRequestNotificationPermission = {},
            onNavigateDailyScreen = { },
            onAction = onAction
        )
    }
}
