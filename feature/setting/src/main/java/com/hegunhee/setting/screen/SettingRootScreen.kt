package com.hegunhee.setting.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.setting.R
import hegunhee.routiner.ui.item.Spinner

@Composable
fun SettingRootScreen(
    onClickDrawer: () -> Unit,
) {
    SettingScreen(
        onClickDrawer = onClickDrawer,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    onClickDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        TopAppBar(
            title = {
                Text(stringResource(R.string.setting))
            },
            navigationIcon = {
                IconButton(onClick = onClickDrawer) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = stringResource(R.string.drawer_open)
                    )
                }
            }
        )
        val settingModifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Black)
            .padding(10.dp)

        

    }
    Text("SettingScreen")
}

@Composable
fun AlarmSetting(
    isAlarmEnabled: Boolean,
    selectedHour: String,
    selectedMinute: String,
    onClickAlarmEnableSwitch: (Boolean) -> Unit,
    onHourChanged : (String) -> Unit,
    onMinuteChanged : (String) -> Unit,
    onClickSaveAlarm : (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.alarm_setting), fontSize = 20.sp)
            Spacer(modifier = modifier.weight(1f))
            Switch(checked = isAlarmEnabled, onCheckedChange = onClickAlarmEnableSwitch)
        }
        if(isAlarmEnabled) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(stringResource(R.string.time_setting))
                Spacer(modifier = modifier.weight(1f))
                Spinner(
                    hourList,
                    selectedHour,
                    onHourChanged,
                )
                Text(":")
                Spinner(
                    minuteList,
                    selectedMinute,
                    onMinuteChanged,
                )
            }
            Button(
                onClick = { onClickSaveAlarm(selectedHour,selectedMinute) },
                modifier = modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.save_alarm))
            }
        }

    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    SettingScreen(
        onClickDrawer = {},
    )
}

@Preview
@Composable
private fun AlarmSettingPreview()  {
    val (isAlarmEnabled, onAlarmSwitchChanged) = remember { mutableStateOf(false) }
    val (selectedHour, onSelectedHourChanged) = remember { mutableStateOf("00") }
    val (selectedMinute, onSelectedMinuteChanged) = remember { mutableStateOf("00") }

    AlarmSetting(
        isAlarmEnabled = isAlarmEnabled,
        selectedHour = selectedHour,
        selectedMinute = selectedMinute,
        onClickAlarmEnableSwitch = onAlarmSwitchChanged,
        onHourChanged = onSelectedHourChanged,
        onMinuteChanged = onSelectedMinuteChanged,
        onClickSaveAlarm = { hour, minute->

        }
    )
}

private val hourList = (0..23).map {
    if (it < 10) {
        "0$it"
    } else {
        it.toString()
    }
}

private val minuteList = listOf("00", "15", "30", "45")
