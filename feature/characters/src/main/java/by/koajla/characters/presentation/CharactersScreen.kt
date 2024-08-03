package by.koajla.characters.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.koajla.characters.presentation.components.CharacterCard

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onTap: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        if (uiState.characters.isEmpty()) {
            item {
                CircularProgressIndicator()
            }
            return@LazyVerticalGrid
        }
        if (uiState.error != null) {
            item {
                Text(text = uiState.error)
            }
        }
        items(uiState.characters) { character ->
            CharacterCard(character = character) {onTap(it)}
        }
    }

}