package com.hegunhee.setting

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import hegunhee.routiner.ui.item.Spinner

@Composable
fun SettingRootScreen(
    viewModel: SettingViewModel = hiltViewModel(),
    onClickDrawer: () -> Unit,
) {

    val context = LocalContext.current

    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else mutableStateOf(true)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasNotificationPermission = isGranted
        }
    )

    val onRequestNotificationPermission : () -> Unit = {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    SettingScreen(
        onClickDrawer = onClickDrawer,
        isAlarmEnabled = viewModel.alarmEnabled.collectAsStateWithLifecycle().value,
        hasNotificationPermission = hasNotificationPermission,
        selectedHour = viewModel.alarmHour.collectAsStateWithLifecycle().value,
        selectedMinute = viewModel.alarmMinute.collectAsStateWithLifecycle().value,
        onClickAlarmEnableSwitch = viewModel::onClickAlarmEnabled,
        onRequestNotificationPermission = onRequestNotificationPermission,
        onHourChanged = viewModel::onAlarmHourChanged,
        onMinuteChanged = viewModel::onAlarmMinuteChanged,
        onClickSaveAlarm = viewModel::onAlarmChanged,
    )

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.alarmState.collect { state ->
                when(state) {
                    AlarmState.Cancel -> {
                        context.cancelAlarm()
                    }
                    is AlarmState.Register -> {
                        context.registerAlarm(hour = state.hour, minute = state.minute)
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingScreen(
    onClickDrawer: () -> Unit,
    isAlarmEnabled: Boolean,
    hasNotificationPermission: Boolean,
    selectedHour: String,
    selectedMinute: String,
    onClickAlarmEnableSwitch: (Boolean) -> Unit,
    onRequestNotificationPermission: () -> Unit,
    onHourChanged: (String) -> Unit,
    onMinuteChanged: (String) -> Unit,
    onClickSaveAlarm: (String, String) -> Unit,
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

        AlarmSetting(
            isAlarmEnabled = isAlarmEnabled,
            hasNotificationPermission = hasNotificationPermission,
            selectedHour = selectedHour,
            selectedMinute = selectedMinute,
            onClickAlarmEnableSwitch = onClickAlarmEnableSwitch,
            onRequestNotificationPermission = onRequestNotificationPermission,
            onHourChanged = onHourChanged,
            onMinuteChanged = onMinuteChanged,
            onClickSaveAlarm = onClickSaveAlarm,
            modifier = settingModifier,
        )
    }
}

@Composable
internal fun AlarmSetting(
    isAlarmEnabled: Boolean,
    hasNotificationPermission : Boolean,
    selectedHour: String,
    selectedMinute: String,
    onClickAlarmEnableSwitch: (Boolean) -> Unit,
    onRequestNotificationPermission : () -> Unit,
    onHourChanged: (String) -> Unit,
    onMinuteChanged: (String) -> Unit,
    onClickSaveAlarm: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.alarm_setting), fontSize = 20.sp)
            Spacer(modifier = modifier.weight(1f))
            Switch(
                checked = isAlarmEnabled && hasNotificationPermission,
                onCheckedChange = { checked ->
                    if(!hasNotificationPermission) {
                        onRequestNotificationPermission()
                    }
                    onClickAlarmEnableSwitch(checked)
                },
                modifier = modifier.testTag(stringResource(R.string.alarm_switch_tag))
            )
        }
        if (isAlarmEnabled && hasNotificationPermission) {
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
                onClick = { onClickSaveAlarm(selectedHour, selectedMinute) },
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
        isAlarmEnabled = true,
        hasNotificationPermission = true,
        selectedHour = "00",
        selectedMinute = "00",
        onClickAlarmEnableSwitch = { },
        onRequestNotificationPermission = {},
        onHourChanged = {},
        onMinuteChanged = {},
        onClickSaveAlarm = { _, _ -> },
    )
}

@Preview
@Composable
private fun AlarmSettingPreview() {
    val (isAlarmEnabled, onAlarmSwitchChanged) = remember { mutableStateOf(false) }
    val (selectedHour, onSelectedHourChanged) = remember { mutableStateOf("00") }
    val (selectedMinute, onSelectedMinuteChanged) = remember { mutableStateOf("00") }

    AlarmSetting(
        isAlarmEnabled = isAlarmEnabled,
        hasNotificationPermission = false,
        selectedHour = selectedHour,
        selectedMinute = selectedMinute,
        onClickAlarmEnableSwitch = onAlarmSwitchChanged,
        onRequestNotificationPermission = { },
        onHourChanged = onSelectedHourChanged,
        onMinuteChanged = onSelectedMinuteChanged,
        onClickSaveAlarm = { hour, minute ->

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
