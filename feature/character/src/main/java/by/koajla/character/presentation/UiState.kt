package by.koajla.character.presentation

import by.koajla.character.domain.model.Character
import by.koajla.character.domain.model.PointData

data class UiState(
    val data: Character? = null,
    val points: List<PointData> = emptyList(),
    val error: String? = null
)
