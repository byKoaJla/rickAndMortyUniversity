package by.koajla.episodes.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.koajla.common.ApiCallResult
import by.koajla.episodes.domain.use_case.GetCharacterWithEpisodesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class CharacterEpisodesViewModel(
    savedStateHandle: SavedStateHandle,
    private val useCase: GetCharacterWithEpisodesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        val characterId: Int = checkNotNull(savedStateHandle["characterId"])
        loadEpisodes(characterId)
    }

    private fun loadEpisodes(id: Int) = useCase(id).onEach { result ->
        when(result) {
            is ApiCallResult.Err -> _uiState.update { it.copy(
                error = result.error.message
            ) }
            is ApiCallResult.Ok -> _uiState.update { it.copy(
                data = result.data
            ) }
        }
    }.launchIn(viewModelScope)
}