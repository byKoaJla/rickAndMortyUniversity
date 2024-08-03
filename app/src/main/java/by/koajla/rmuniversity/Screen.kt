package by.koajla.rmuniversity

sealed class Screen(val route: String) {
    data object Character           : Screen(CHARACTER_ROUTE)
    data object CharacterEpisodes   : Screen(CHARACTER_EPISODES_ROUTE)
    data object Characters          : Screen(CHARACTERS_ROUTE)

    companion object {
        private const val CHARACTER_ROUTE = "character"
        private const val CHARACTER_EPISODES_ROUTE = "episodes"
        private const val CHARACTERS_ROUTE = "characters"
    }
}