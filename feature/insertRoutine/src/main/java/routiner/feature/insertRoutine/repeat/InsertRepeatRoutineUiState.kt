package routiner.feature.insertRoutine.repeat

import routiner.core.model.Category
import routiner.core.model.DayOfWeek

sealed interface InsertRepeatRoutineUiState {

    data object Init : InsertRepeatRoutineUiState

    data class Categories(
        val categories: List<Category>,
        val dayOfWeeks : List<DayOfWeek>,
    ) : InsertRepeatRoutineUiState

}
