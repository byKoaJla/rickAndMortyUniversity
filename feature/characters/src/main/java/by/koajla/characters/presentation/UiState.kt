package by.koajla.characters.presentation

import by.koajla.characters.domain.model.Characters

data class UiState(
    val characters: List<Characters> = emptyList(),
    val error: String? = null
)
