package by.koajla.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.koajla.characters.domain.use_case.GetCharactersUseCase
import by.koajla.common.ApiCallResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class CharactersViewModel(
    private val useCase: GetCharactersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadFirstPage()
    }

    private fun loadFirstPage(page: Int = 1) = useCase(page)
        .onEach { result ->
            when(result) {
                is ApiCallResult.Err -> _uiState.update { it.copy(error = result.error.message) }
                is ApiCallResult.Ok -> _uiState.update { it.copy(characters = result.data) }
            }
        }.launchIn(viewModelScope)
}