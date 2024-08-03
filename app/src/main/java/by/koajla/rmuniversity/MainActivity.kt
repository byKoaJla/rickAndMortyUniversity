package by.koajla.rmuniversity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import by.koajla.character.presentation.CharacterScreen
import by.koajla.character.presentation.CharacterViewModel
import by.koajla.characters.presentation.CharactersScreen
import by.koajla.rmuniversity.ui.theme.RMUniversityTheme
import by.koajla.ui.AppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigation(navController)
                    },
                    contentWindowInsets = WindowInsets.statusBars.add(
                        WindowInsets(left = 5.dp, right = 5.dp)
                    )
                ) { ip ->
                    AppNavGraph(
                        navHostController = navController,
                        showCharacterDetail = { state, getId ->
                            CharacterScreen(modifier = Modifier.padding(ip), uiState = state) { id -> getId(id) }
                        },
                        charactersPage = {state, getId ->
                            CharactersScreen(
                                modifier = Modifier.padding(ip),
                                uiState = state
                            ) {getId(it)}
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigation(
    navController: NavController
) {
    val items = listOf(
        NavItem(
            route = Screen.Characters.route,
            icon = R.drawable.outline_dashboard_24,
            label = R.string.home
        )
    )
    val backStackEntry = navController.currentBackStackEntryAsState()
        .value?.destination?.route
    BottomAppBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.route == backStackEntry,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(painter = painterResource(id = item.icon),
                    contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = item.label))
                })
        }
    }
}