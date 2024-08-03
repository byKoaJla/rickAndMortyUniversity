package by.koajla.character.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.koajla.character.R
import by.koajla.character.domain.model.Character
import by.koajla.character.domain.model.PointData
import by.koajla.character.domain.use_case.GetCharacterUseCase
import by.koajla.common.ApiCallResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class CharacterViewModel(
    savedStateHandle: SavedStateHandle,
    private val useCase: GetCharacterUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        val characterId: Int = checkNotNull(savedStateHandle["characterId"])
        Log.d("Character_Id", "$characterId ")
        loadCharacter(characterId)
    }

    private fun loadCharacter(id: Int) {
        useCase(id).onEach { result ->
            when (result) {
                is ApiCallResult.Err -> handleError(result.error.message)
                is ApiCallResult.Ok -> handleSuccess(result.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun handleError(errorMessage: String?) {
        _uiState.update { currentState ->
            currentState.copy(error = errorMessage ?: "Unknown error occurred.")
        }
    }

    private fun handleSuccess(data: Character?) {
        val points = buildList {
            add(PointData(R.string.last_known_location, data?.location))
            add(PointData(R.string.species, data?.species))
            add(PointData(R.string.gender, data?.gender?.displayName))
            add(PointData(R.string.origin, data?.origin))
            add(PointData(R.string.first_seen_in, data?.episodes?.getOrNull(0)?.name))
            add(PointData(R.string.episode_count, data?.episodes?.count()?.toString() ?: "0"))
        }

        _uiState.update { currentState ->
            currentState.copy(data = data, points = points)
        }
    }
}