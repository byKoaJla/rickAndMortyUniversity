package by.koajla.characters.domain.model

import androidx.compose.ui.graphics.Color

sealed class Status(val displayName: String, val color: Color) {
    data object Alive   : Status(displayName = "Alive",color = Color.Green)
    data object Dead    : Status(displayName = "Dead", color = Color.Red)
    data object Unknown : Status(displayName = "Unknown", color = Color.Yellow)
}