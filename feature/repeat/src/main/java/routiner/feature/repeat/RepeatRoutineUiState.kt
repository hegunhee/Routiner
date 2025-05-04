package routiner.feature.repeat

import routiner.core.model.RepeatRoutine

sealed interface RepeatRoutineUiState {

    data object Init : RepeatRoutineUiState

    data object Empty : RepeatRoutineUiState

    data class Success(
        val items : List<RepeatRoutine>
    ) : RepeatRoutineUiState

}
