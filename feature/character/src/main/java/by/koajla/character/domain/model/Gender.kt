package by.koajla.character.domain.model

sealed class Gender(val displayName: String) {
    data object Female      : Gender(FEMALE_GENDER)
    data object Male        : Gender(MALE_GENDER)
    data object Genderless  : Gender(GENDERLESS_GENDER)
    data object Unknown     : Gender(UNKNOWN_GENDER)

    companion object {
        private const val FEMALE_GENDER = "female"
        private const val MALE_GENDER = "male"
        private const val GENDERLESS_GENDER = "genderless"
        private const val UNKNOWN_GENDER = "unknown"
    }
}