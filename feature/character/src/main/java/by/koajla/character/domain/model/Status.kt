package by.koajla.character.domain.model

import androidx.compose.ui.graphics.Color

sealed class Status(val displayName: String, val color: Color) {
    data object Alive   : Status(ALIVE_STATUS, ALIVE_STATUS_COLOR)
    data object Dead    : Status(DEAD_STATUS, DEAD_STATUS_COLOR)
    data object Unknown : Status(UNKNOWN_STATUS, UNKNOWN_STATUS_COLOR)

    companion object {
        private const val ALIVE_STATUS = "alive"
        private val ALIVE_STATUS_COLOR = Color.Green

        private const val DEAD_STATUS = "dead"
        private val DEAD_STATUS_COLOR = Color.Red

        private const val UNKNOWN_STATUS = "unknown"
        private val UNKNOWN_STATUS_COLOR = Color.Yellow
    }
}