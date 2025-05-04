package routiner.feature.repeat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import routiner.core.domain.usecase.repeatRoutine.DeleteRepeatRoutineUseCase
import routiner.core.domain.usecase.repeatRoutine.GetRepeatRoutinesFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepeatRoutineViewModel @Inject constructor(
    private val getRepeatRoutinesFlowUseCase: GetRepeatRoutinesFlowUseCase,
    private val deleteRepeatRoutineUseCase: DeleteRepeatRoutineUseCase,
) : ViewModel() {

    val uiState : StateFlow<RepeatRoutineUiState> = getRepeatRoutinesFlowUseCase().map {
        if(it.isEmpty()) {
            RepeatRoutineUiState.Empty
        } else {
            RepeatRoutineUiState.Success(it)
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = RepeatRoutineUiState.Init,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun onClickRepeatRoutineDelete(repeatRoutineText : String) {
        viewModelScope.launch {
            deleteRepeatRoutineUseCase(repeatRoutineText)
        }
    }
}
