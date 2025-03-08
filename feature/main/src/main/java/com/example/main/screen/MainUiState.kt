package com.example.main.screen

import androidx.annotation.StringRes
import com.example.main.R

sealed class MainUiState(
    @StringRes val stringId: Int,
) {

    data object Init : MainUiState(R.string.init_message)

    data object FirstOpenApp : MainUiState(R.string.first_app_open_message)

    data object InitDate : MainUiState(R.string.init_date_message)

    data object InsertRepeatRoutine : MainUiState(R.string.insert_repeat_routine_message)

    data object Success : MainUiState(R.string.success_message)

}
