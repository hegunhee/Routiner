package com.example.main.screen

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.main.R
import hegunhee.routiner.designsystem.theme.DarkGreen

@Composable
fun MainRootScreen(
    successRoute: String,
    onNavigateDailyScreen: (String) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val uiState = mainViewModel.uiState.collectAsStateWithLifecycle().value

    val permissionLauncher = rememberLauncherForActivityResult (
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {  }
    )

    val onRequestNotificationPermission = {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    MainScreen(
        uiState = uiState,
        successRoute = successRoute,
        onRequestNotificationPermission = onRequestNotificationPermission,
        onNavigateDailyScreen = onNavigateDailyScreen,
        onAction = mainViewModel::onAction
    )
}

@Composable
fun MainScreen(
    uiState: MainUiState,
    successRoute: String,
    onRequestNotificationPermission : () -> Unit,
    onNavigateDailyScreen: (String) -> Unit,
    onAction: (MainUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(uiState) {
        when(uiState) {
            MainUiState.Success -> {
                onNavigateDailyScreen(successRoute)
            }
            MainUiState.FirstOpenApp -> {
                onRequestNotificationPermission()
                onAction(uiState)
            }
            else -> {
                onAction(uiState)
            }
        }
        if (uiState == MainUiState.Success) {
            onNavigateDailyScreen(successRoute)
        } else {
            onAction(uiState)
        }
    }

    Column {
        Text(
            stringResource(R.string.app_name),
            modifier = modifier.padding(top = 100.dp, start = 30.dp),
            fontSize = 40.sp,
            color = DarkGreen
        )
        Text(
            stringResource(R.string.app_description),
            modifier = modifier.padding(top = 10.dp, start = 30.dp),
            fontSize = 20.sp,
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            stringResource(uiState.stringId),
            modifier = modifier
                .padding(bottom = 50.dp)
                .fillMaxWidth(),
            fontSize = 20.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )
    }

}


@Composable
@Preview
private fun MainScreenPreview() {
    val context = LocalContext.current
    MainScreen(
        uiState = MainUiState.Success,
        successRoute = "",
        onRequestNotificationPermission = {},
        onAction = {},
        onNavigateDailyScreen = {
            Toast.makeText(context, "이동합니당", Toast.LENGTH_SHORT).show()
        },
    )
}
