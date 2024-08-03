package by.koajla.character.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.koajla.character.R
import by.koajla.character.domain.model.Character
import by.koajla.character.domain.model.Episode
import by.koajla.character.domain.model.Gender
import by.koajla.character.domain.model.PointData
import by.koajla.character.domain.model.Status
import by.koajla.character.presentation.components.Point
import by.koajla.character.presentation.components.StatusPoint
import by.koajla.ui.AppTheme
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage

@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onShowEpisodes: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(
            horizontal = 10.dp,
            vertical = 10.dp
        )
    ) {
        if (uiState.data == null) {
            item {
                CircularProgressIndicator()
            }
            return@LazyColumn
        }
        item {
            Text(
                text = uiState.data.name,
                style = MaterialTheme.typography.displaySmall.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
        item {
            val context = LocalContext.current
            val imageLoader = ImageLoader(context)
                .newBuilder()
                .crossfade(true)
                .build()
            SubcomposeAsyncImage(
                model = uiState.data.image,
                contentDescription = uiState.data.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .aspectRatio(1f),
                loading = {
                    CircularProgressIndicator()
                },
                imageLoader = imageLoader
            )
        }
        item {
            StatusPoint(status = uiState.data.status)
        }
        items(uiState.points) { point ->
            Point(point = point)
        }
        item {
            OutlinedButton(
                onClick = { onShowEpisodes(uiState.data.id) },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.show_all_episodes),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Preview
@Composable
private fun CharacterScreenPreview() {
    AppTheme {
        CharacterScreen(
            uiState = UiState(
            data = Character(
                1,
                "Rick Sunchez",
                "",
                Status.Alive,
                "Test 1",
                "Test 2",
                Gender.Male,
                "Test 3",
                "Test 4",
                listOf(Episode("", "")),
                "Test 5"
            ),
            points = buildList {
                add(PointData(R.string.last_known_location, "Location"))
                add(PointData(R.string.species, "Species"))
                add(PointData(R.string.gender, "Gender"))
                add(PointData(R.string.origin, "Origin"))
                add(PointData(R.string.first_seen_in, "First seen in"))
                add(PointData(R.string.episode_count, "1"))
            }
        ), onShowEpisodes = {})
    }
}