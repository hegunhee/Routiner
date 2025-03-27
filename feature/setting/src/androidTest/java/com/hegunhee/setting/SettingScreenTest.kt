package com.hegunhee.setting

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var context: Context

    @Before
    fun initContext() {
        context = composeTestRule.activity.baseContext
    }

    @Test
    fun givenAlarmEnabledTrue_whenScreening_shownNotiDescription() {
        composeTestRule.setContent {
            ShowSettingScreen(
                isAlarmEnabled = true
            )
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.time_setting))
            .assertIsDisplayed()
    }

    @Test
    fun givenAlarmEnabled_whenClickEnableSwitch_shownNotNotiDescription() {
        composeTestRule.setContent {
            var alarmEnabled by remember { mutableStateOf(true) }

            val onClickAlarmSwitch: (Boolean) -> Unit = { newValue ->
                alarmEnabled = newValue
            }

            ShowSettingScreen(
                isAlarmEnabled = alarmEnabled,
                onClickAlarmEnableSwitch = onClickAlarmSwitch
            )
        }

        composeTestRule
            .onNodeWithText(context.getString(R.string.time_setting))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(context.getString(R.string.alarm_switch_tag))
            .performClick()

        composeTestRule
            .onNodeWithText(context.getString(R.string.time_setting))
            .assertDoesNotExist()
    }

    @Composable
    private fun ShowSettingScreen(
        onClickDrawer: () -> Unit = {},
        isAlarmEnabled: Boolean = false,
        selectedHour: String = "00",
        selectedMinute: String = "00",
        onClickAlarmEnableSwitch: (Boolean) -> Unit = {},
        onHourChanged: (String) -> Unit = {},
        onMinuteChanged: (String) -> Unit = {},
        onClickSaveAlarm: (String, String) -> Unit = { _, _ -> },
    ) {
        SettingScreen(
            onClickDrawer = onClickDrawer,
            isAlarmEnabled = isAlarmEnabled,
            selectedHour = selectedHour,
            selectedMinute = selectedMinute,
            onClickAlarmEnableSwitch = onClickAlarmEnableSwitch,
            onHourChanged = onHourChanged,
            onMinuteChanged = onMinuteChanged,
            onClickSaveAlarm = onClickSaveAlarm,
        )
    }
}
