package com.example.main.screen

import android.widget.Toast
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
    onNavigateDailyScreen: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val uiState = mainViewModel.uiState.collectAsStateWithLifecycle().value

    MainScreen(
        uiState = uiState,
        onNavigateDailyScreen = onNavigateDailyScreen,
        onAction = mainViewModel::onAction
    )
}

@Composable
fun MainScreen(
    uiState: MainUiState,
    onNavigateDailyScreen: () -> Unit,
    onAction: (MainUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(uiState) {
        if (uiState == MainUiState.Success) {
            onNavigateDailyScreen()
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
        onAction = {},
        onNavigateDailyScreen = {
            Toast.makeText(context, "이동합니당", Toast.LENGTH_SHORT).show()
        },
    )
}
