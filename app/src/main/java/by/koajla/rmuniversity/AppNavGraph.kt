package by.koajla.rmuniversity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import by.koajla.character.presentation.CharacterViewModel
import by.koajla.character.presentation.UiState
import by.koajla.characters.presentation.CharactersViewModel
import by.koajla.episodes.presentation.CharacterEpisodesScreen
import by.koajla.episodes.presentation.CharacterEpisodesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    showCharacterDetail: @Composable (UiState, (Int) -> Unit) -> Unit,
    charactersPage: @Composable (by.koajla.characters.presentation.UiState, (Int) -> Unit) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Characters.route)
    {
        composable(Screen.Characters.route) {
            val viewmodel = koinViewModel<CharactersViewModel>()
            val uiState by viewmodel.uiState.collectAsState()
            charactersPage(uiState) { id -> navHostController.navigate(Screen.Character.route+"/$id")}
        }

        composable(Screen.Character.route + "/{characterId}",
            arguments = listOf(navArgument("characterId") {
                type = NavType.IntType
                defaultValue = 10
            })
        ) {
            val viewmodel = koinViewModel<CharacterViewModel>()
            val uiState by viewmodel.uiState.collectAsState()
            showCharacterDetail(uiState) { id -> navHostController.navigate(Screen.CharacterEpisodes.route+"/$id")}
        }

        composable(Screen.CharacterEpisodes.route + "/{characterId}",
            arguments = listOf(navArgument("characterId") {
                type = NavType.IntType
                defaultValue = 1
            })
        ) {
            val viewmodel = koinViewModel<CharacterEpisodesViewModel>()
            val uiState by viewmodel.uiState.collectAsState()
            CharacterEpisodesScreen(uiState = uiState) {
                navHostController.popBackStack()
            }
        }
    }
}