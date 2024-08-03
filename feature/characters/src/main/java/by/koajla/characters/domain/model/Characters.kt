package by.koajla.characters.domain.model

import androidx.compose.ui.graphics.Color

data class Characters(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String?,
    val location: String,
    val episode: String,
    val image: String
)

