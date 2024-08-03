package by.koajla.episodes.presentation

import by.koajla.episodes.domain.model.CharacterWithEpisodes

data class UiState(
    val data: CharacterWithEpisodes? = null,
    val error: String? = null
)
