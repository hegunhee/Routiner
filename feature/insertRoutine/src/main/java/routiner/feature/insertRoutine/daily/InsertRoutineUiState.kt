package routiner.feature.insertRoutine.daily

import routiner.core.model.Category

sealed interface InsertRoutineUiState {

    data object Init : InsertRoutineUiState

    data class Categories(
        val items: List<Category>
    ) : InsertRoutineUiState

}
